package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.key.NoKeysAvailableException;
import at.vaaniicx.lap.mapper.shoppingcart.ShoppingCartResponseMapper;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.entity.pk.ShoppingCartGamePk;
import at.vaaniicx.lap.model.request.shoppingcart.AddToShoppingCartRequest;
import at.vaaniicx.lap.model.response.game.StatisticGameResponse;
import at.vaaniicx.lap.model.response.shoppingcart.ShoppingCartResponse;
import at.vaaniicx.lap.model.response.shoppingcart.SlimShoppingCartResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.KeyCodeService;
import at.vaaniicx.lap.service.ShoppingCartGameService;
import at.vaaniicx.lap.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartGameService shoppingCartGameService;
    private final GameService gameService;
    private final KeyCodeService keyCodeService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ShoppingCartGameService shoppingCartGameService,
                                  GameService gameService, KeyCodeService keyCodeService) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartGameService = shoppingCartGameService;
        this.gameService = gameService;
        this.keyCodeService = keyCodeService;
    }

    /**
     * Liefert alle Warenkörbe.
     *
     * @return - Liste aller Warenkörbe
     */
    @GetMapping
    public ResponseEntity<List<SlimShoppingCartResponse>> getAllCarts() {

        List<SlimShoppingCartResponse> responses = shoppingCartService.getAllShoppingCarts()
                .stream()
                .map(ShoppingCartResponseMapper.INSTANCE::entityToSlimResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Liefert den Warenkorb zur ID.
     *
     * @param shoppingCartId - ID zum Warenkorb
     * @return - Warenkorb zur ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<SlimShoppingCartResponse> getCartById(@PathVariable("id") Long shoppingCartId) {

        ShoppingCartEntity cart = shoppingCartService.getShoppingCartById(shoppingCartId);

        return ResponseEntity.ok(ShoppingCartResponseMapper.INSTANCE.entityToSlimResponse(cart));
    }

    /**
     * Liefert den Warenkorb einer Person.
     *
     * @param personId - ID zur Person
     * @return - Warenkorb zur Person
     */
    @GetMapping("/person/{id}")
    public ResponseEntity<ShoppingCartResponse> getCartByPersonId(@PathVariable("id") Long personId) {

        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(personId);

        cart.setTotalPrice(shoppingCartService.calculateShoppingCartSum(cart)); // Gesamtsumme berechnen

        return ResponseEntity.ok(ShoppingCartResponseMapper.INSTANCE.entityToResponse(cart));
    }

    /**
     * Fügt einen Artikel zum Warenkorb hinzu.
     *
     * @param request - Aktualisierungs-Request
     * @return - Aktualisierter Warenkorb
     */
    @PostMapping("/person/add")
    public ResponseEntity<ShoppingCartResponse> addItemToShoppingCart(@RequestBody @Validated AddToShoppingCartRequest request) {

        // Shopping-Cart des Benutzers holen
        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(request.getPersonId());
        // Inhalt des Shopping-Carts holen
        List<ShoppingCartGameEntity> shoppingCartGames = shoppingCartGameService.getShoppingCartGameByShoppingCartId(cart.getId());

        Optional<ShoppingCartGameEntity> foundEntry =
                shoppingCartGames.stream().filter(scg -> scg.getGame() != null && scg.getGame().getId().equals(request.getGameId())).findFirst();

        ShoppingCartGameEntity game;
        if (foundEntry.isPresent()) { // Gibt es bereits einen Eintrag des Spiels?
            game = foundEntry.get();

            byte newAmount = (byte) (game.getAmount() + request.getAmount());  // Vorhandenen Eintrag updaten
            Long available = keyCodeService.getKeyCountByGameIdAndSold(game.getId().getGameId(), false);

            if (newAmount <= available) {
                game.setAmount(newAmount < 0 ? 0 : newAmount);
            } else {
                throw new NoKeysAvailableException(); // Nicht genügend Schlüssel vorhanden
            }
        } else {
            game = new ShoppingCartGameEntity(); // Neuen Eintrag erstellen
            game.setId(new ShoppingCartGamePk(cart.getId(), request.getGameId()));
            game.setGame(gameService.getGameById(request.getGameId()));
            game.setShoppingCart(cart);
            game.setAmount(request.getAmount());
        }
        shoppingCartGameService.save(game); // Persistieren

        double totalPrice = shoppingCartService.calculateShoppingCartSum(cart); // Gesamtsumme berechnen
        cart.setTotalPrice(totalPrice);
        shoppingCartService.save(cart); // Persistieren

        return ResponseEntity.ok(ShoppingCartResponseMapper.INSTANCE.entityToResponse(cart));
    }

    /**
     * Löscht den Inhalt des Warenkorbes einer Person.
     *
     * @param personId - ID zur Person
     * @return - Leerer Warenkorb der Person
     */
    @DeleteMapping("/person/{id}/clear")
    public ResponseEntity<ShoppingCartResponse> clearShoppingCartForPerson(@PathVariable("id") Long personId) {

        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(personId);
        cart.setTotalPrice(0); // Gesamtsumme setzen

        shoppingCartGameService.deleteAllById(cart.getGames());
        cart.getGames().removeAll(cart.getGames());

        shoppingCartService.save(cart); // Persistieren

        return ResponseEntity.ok(ShoppingCartResponseMapper.INSTANCE.entityToResponse(cart));
    }
}
