package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.ShoppingCartDTO;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.entity.pk.ShoppingCartGamePk;
import at.vaaniicx.lap.model.mapper.ShoppingCartMapper;
import at.vaaniicx.lap.model.request.shoppingcart.AddToShoppingCartRequest;
import at.vaaniicx.lap.model.response.shoppingcart.ShoppingCartResponse;
import at.vaaniicx.lap.model.response.shoppingcart.ShoppingCartGameResponse;
import at.vaaniicx.lap.service.GamePictureService;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.ShoppingCartGameService;
import at.vaaniicx.lap.service.ShoppingCartService;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    private GamePictureService gamePictureService;

    @GetMapping
    public List<ShoppingCartDTO> getAll() {
        return shoppingCartService.getAllShoppingCarts().stream().map(ShoppingCartMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponse> getById(@PathVariable("id") Long shoppingCartId) {
        ShoppingCartEntity cart = shoppingCartService.getShoppingCartById(shoppingCartId);

        ShoppingCartResponse response =
                ShoppingCartResponse
                        .builder()
                        .shoppingCartId(shoppingCartId)
                        .personId(cart.getPerson().getId())
                        .totalPrice(cart.getTotalPrice())
                        .shoppingCartGames(cart.getGames().stream().map(scg ->
                                        ShoppingCartGameResponse
                                                .builder()
                                                .gameId(scg.getGame().getId())
                                                .title(scg.getGame().getTitle())
                                                .thumb(ImageConversionHelper.blobToByteArray(gamePictureService.getThumbPictureForGameId(scg.getGame().getId()).getImage()))
                                                .price(scg.getGame().getPrice())
                                                .amount(scg.getAmount())
                                                .build())
                                .collect(Collectors.toList()))
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<ShoppingCartResponse> getByPersonId(@PathVariable("id") Long personId) {
        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(personId);

        ShoppingCartResponse response =
                ShoppingCartResponse
                        .builder()
                        .shoppingCartId(cart.getId())
                        .personId(personId)
                        .totalPrice(cart.getTotalPrice())
                        .shoppingCartGames(cart.getGames().stream().map(scg ->
                                        ShoppingCartGameResponse
                                                .builder()
                                                .gameId(scg.getGame().getId())
                                                .title(scg.getGame().getTitle())
                                                .thumb(ImageConversionHelper.blobToByteArray(gamePictureService.getThumbPictureForGameId(scg.getGame().getId()).getImage()))
                                                .price(scg.getGame().getPrice())
                                                .amount(scg.getAmount())
                                                .build())
                                .collect(Collectors.toList()))
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
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
            totalPrice += BigDecimal.valueOf(g.getPrice()).multiply(BigDecimal.valueOf(entry.getAmount())).doubleValue();
        }

        cart.setTotalPrice(totalPrice);
        shoppingCartService.saveShoppingCart(cart);

        ShoppingCartResponse response =
                ShoppingCartResponse
                        .builder()
                        .shoppingCartId(cart.getId())
                        .personId(cart.getPerson().getId())
                        .totalPrice(cart.getTotalPrice())
                        .shoppingCartGames(cart.getGames().stream().map(scg ->
                                        ShoppingCartGameResponse
                                                .builder()
                                                .gameId(scg.getGame().getId())
                                                .title(scg.getGame().getTitle())
                                                .thumb(ImageConversionHelper.blobToByteArray(gamePictureService.getThumbPictureForGameId(scg.getGame().getId()).getImage()))
                                                .price(scg.getGame().getPrice())
                                                .amount(scg.getAmount())
                                                .build())
                                .collect(Collectors.toList()))
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
