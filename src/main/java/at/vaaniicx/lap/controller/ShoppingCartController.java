package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.ShoppingCartDTO;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.entity.pk.ShoppingCartGamePk;
import at.vaaniicx.lap.model.mapper.ShoppingCartMapper;
import at.vaaniicx.lap.model.request.shoppingcart.AddToShoppingCartRequest;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.ShoppingCartGameService;
import at.vaaniicx.lap.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartGameService shoppingCartGameService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<ShoppingCartDTO> getAll() {
        return shoppingCartService.getAllShoppingCarts().stream().map(ShoppingCartMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ShoppingCartDTO getById(@PathVariable("id") Long shoppingCartId) {
        return ShoppingCartMapper.toDto(shoppingCartService.getShoppingCartById(shoppingCartId));
    }

    @GetMapping("/person/{id}")
    public ShoppingCartDTO getByPersonId(@PathVariable("id") Long personId) {
        return ShoppingCartMapper.toDto(shoppingCartService.getShoppingCartByPersonId(personId));
    }

    @PostMapping("/person/add")
    public ResponseEntity<ShoppingCartDTO> addItemToShoppingCart(@RequestBody @Validated AddToShoppingCartRequest request) {

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
            game.setAmount((byte) (game.getAmount() + request.getAmount()));
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
            GameEntity g = entry.getGame();
            totalPrice += g.getPrice() * entry.getAmount();
        }

        cart.setTotalPrice(totalPrice);
        shoppingCartService.saveShoppingCart(cart);

        return new ResponseEntity<>(ShoppingCartMapper.toDto(cart), HttpStatus.OK);
    }
}
