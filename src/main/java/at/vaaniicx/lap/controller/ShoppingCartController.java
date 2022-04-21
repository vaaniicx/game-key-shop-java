package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.shoppingcart.ShoppingCartResponseMapper;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.entity.pk.ShoppingCartGamePk;
import at.vaaniicx.lap.model.request.shoppingcart.AddToShoppingCartRequest;
import at.vaaniicx.lap.model.response.shoppingcart.ShoppingCartResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.ShoppingCartGameService;
import at.vaaniicx.lap.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    private final  ShoppingCartService shoppingCartService;
    private final  ShoppingCartGameService shoppingCartGameService;
    private final  GameService gameService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ShoppingCartGameService shoppingCartGameService,
                                  GameService gameService) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartGameService = shoppingCartGameService;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartResponse>> getAllCarts() {

        List<ShoppingCartResponse> shoppingCartResponses = shoppingCartService.getAllShoppingCarts()
                .stream()
                .map(ShoppingCartResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(shoppingCartResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponse> getCartById(@PathVariable("id") Long shoppingCartId) {

        ShoppingCartEntity cart = shoppingCartService.getShoppingCartById(shoppingCartId);

        return ResponseEntity.ok(ShoppingCartResponseMapper.INSTANCE.entityToResponse(cart));
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<ShoppingCartResponse> getCartByPersonId(@PathVariable("id") Long personId) {

        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(personId);

        return ResponseEntity.ok(ShoppingCartResponseMapper.INSTANCE.entityToResponse(cart));
    }

    @PostMapping("/person/add")
    public ResponseEntity<ShoppingCartResponse> addItemToShoppingCart(@RequestBody @Validated AddToShoppingCartRequest request) {

        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(request.getPersonId());
        List<ShoppingCartGameEntity> shoppingCartGames = shoppingCartGameService.getShoppingCartGameByShoppingCartId(cart.getId());

        boolean foundEntry =
                shoppingCartGames.stream()
                        .filter(Objects::nonNull)
                        .anyMatch(scg -> scg.getGame() != null && scg.getGame().getId().equals(request.getGameId()));

        ShoppingCartGameEntity game;
        if (foundEntry) {
            // Vorhandenen Eintrag updaten
            game = shoppingCartGames.stream()
                    .filter(Objects::nonNull)
                    .filter(scg -> scg.getGame() != null && scg.getGame().getId().equals(request.getGameId()))
                    .findFirst().get();

            byte newAmount = (byte) (game.getAmount() + request.getAmount());
            game.setAmount(newAmount < 0 ? 0 : newAmount);
        } else {
            // Neuen Eintrag
            game = new ShoppingCartGameEntity();
            game.setId(new ShoppingCartGamePk(cart.getId(), request.getGameId()));
            game.setGame(gameService.getGameById(request.getGameId()));
            game.setShoppingCart(cart);
            game.setAmount(request.getAmount());
        }

        shoppingCartGameService.save(game);

        List<ShoppingCartGameEntity> allEntries = shoppingCartGameService.getShoppingCartGameByShoppingCartId(cart.getId());
        double totalPrice = 0;
        for (ShoppingCartGameEntity entry : allEntries) {
            if (entry.getAmount() <= 0) {
                shoppingCartGameService.deleteAllById(Collections.singleton(entry));
                cart.getGames().remove(entry);
            }

            GameEntity g = entry.getGame();
            System.out.println("entry amount: " + entry.getAmount());
            totalPrice += BigDecimal.valueOf(g.getPrice()).multiply(BigDecimal.valueOf(entry.getAmount())).doubleValue();
        }

        cart.setTotalPrice(totalPrice);
        shoppingCartService.save(cart);

        return ResponseEntity.ok(ShoppingCartResponseMapper.INSTANCE.entityToResponse(cart));
    }

    @DeleteMapping("/person/{id}/clear")
    public ResponseEntity<ShoppingCartResponse> clearShoppingCartForPerson(@PathVariable("id") Long personId) {

        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(personId);

        cart.setTotalPrice(0);
        shoppingCartGameService.deleteAllById(cart.getGames());
        cart.getGames().removeAll(cart.getGames());
        shoppingCartService.save(cart);

        return ResponseEntity.ok(ShoppingCartResponseMapper.INSTANCE.entityToResponse(cart));
    }
}
