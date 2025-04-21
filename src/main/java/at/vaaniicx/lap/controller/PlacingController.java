package at.vaaniicx.lap.controller;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.vaaniicx.lap.exception.key.NoKeysAvailableException;
import at.vaaniicx.lap.mapper.placing.PlacingResponseMapper;
import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.model.entity.PlacingEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.entity.pk.PlacingDetailsPk;
import at.vaaniicx.lap.model.response.game.StatisticGameResponse;
import at.vaaniicx.lap.model.response.placing.PlacingResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.KeyCodeService;
import at.vaaniicx.lap.service.PlacingDetailsService;
import at.vaaniicx.lap.service.PlacingService;
import at.vaaniicx.lap.service.ShoppingCartGameService;
import at.vaaniicx.lap.service.ShoppingCartService;
import at.vaaniicx.lap.service.UserService;

@RestController
@RequestMapping("/placing")
public class PlacingController {

    private final PlacingService placingService;
    private final UserService userService;
    private final GameService gameService;
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartGameService shoppingCartGameService;
    private final PlacingDetailsService placingDetailsService;
    private final KeyCodeService keyCodeService;

    @Autowired
    public PlacingController(PlacingService placingService, UserService userService, GameService gameService,
                             ShoppingCartService shoppingCartService, ShoppingCartGameService shoppingCartGameService,
                             PlacingDetailsService placingDetailsService, KeyCodeService keyCodeService) {
        this.placingService = placingService;
        this.userService = userService;
        this.gameService = gameService;
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartGameService = shoppingCartGameService;
        this.placingDetailsService = placingDetailsService;
        this.keyCodeService = keyCodeService;
    }

    /**
     * Liefer alle Bestellungen.
     *
     * @return - Liste aller Bestellungen
     */
    @GetMapping
    public ResponseEntity<List<PlacingResponse>> getAllPlacings() {

        List<PlacingResponse> placingResponses = placingService.getAllPlacings()
                .stream()
                .map(PlacingResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(placingResponses);
    }

    /**
     * Erstellen/Durchführen einer Bestellung.
     *
     * @param userId - Person zur Bestellung
     * @return - Erstellte Bestellung
     */
    @GetMapping("/{id}/create")
    public ResponseEntity<PlacingResponse> createPlacing(@PathVariable("id") Long userId) {

        // User aus der Datenbank mit der ID holen
        UserEntity user = userService.getUserById(userId);
        PersonEntity person = user.getPerson();

        // Warenkorb mit der Person-ID des Users aus der Datenbank holen
        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(person.getId());

        // Sind genügend Keys für alle Spiele im Warenkorb vorhanden?
        boolean available = isKeysAvailable(cart);
        if (!available) {
            throw new NoKeysAvailableException(); // Es gibt nicht für alle Spiele genügend Schlüssel
        }

        // Bestellungs-Objekt erstellen
        PlacingEntity placing = new PlacingEntity();
        placing.setPlacingDate(Instant.now()); // Werte setzen
        placing.setTotalPrice(cart.getTotalPrice());
        placing.setPerson(person);

        PlacingEntity savedPlacing = placingService.save(placing); // Persistieren

        // Bestelldetails erstellen
        Set<PlacingDetailsEntity> placingDetails = new HashSet<>();

        // Top 3 der schlechtesten Spiele -> 10% Rabatt
        List<Long> statisticGameIds = gameService.getAllGamesOrderByTitle().stream().map(e -> new StatisticGameResponse(e.getId(), e.getTitle(),
                        keyCodeService.getKeyCountByGameIdAndSold(e.getId(), true)))
                .sorted(Comparator.comparing(StatisticGameResponse::getKeysSold))
                .map(StatisticGameResponse::getId)
                .collect(Collectors.toList());

        statisticGameIds.subList(3, statisticGameIds.size()).clear(); // Nur die 3 schlechtesten in der Liste behalten

        cart.getGames().forEach(item -> {
            // Alle verfügbaren Schlüssel pro Spiel aus der Datenbank abrufen
            List<KeyCodeEntity> availableKeyCodes = keyCodeService.getAllAvailableKeyCodesByGameId(item.getGame().getId());

            for (int i = 0; i < item.getAmount(); i++) { // Anzahl der bestellten Schlüssel kaufen
                KeyCodeEntity keyCode = availableKeyCodes.get(i); // Schlüssel-Objekt befüllen
                keyCode.setPerson(person); // Werte setzen
                keyCode.setSold(true);

                // Persistieren
                KeyCodeEntity savedKeyCode = keyCodeService.save(keyCode); // (-> Schlüssel wurde nun an Benutzer X verkauft)

                // Bestellungsdetails-Objekt erstellen
                PlacingDetailsEntity details = new PlacingDetailsEntity();
                details.setId(new PlacingDetailsPk(savedPlacing.getId(), savedKeyCode.getId()));
                details.setPlacing(savedPlacing); // Werte setzen
                // Ist das Spiel ein reduziertes Spiel (=> Top-3)?
                details.setPrice(statisticGameIds.contains(keyCode.getGame().getId()) ?
                        savedKeyCode.getGame().getPrice() * 0.9 : savedKeyCode.getGame().getPrice());
                details.setKeyCode(savedKeyCode);

                // Bestellungsdetails-Objekt persistieren
                PlacingDetailsEntity savedDetails = placingDetailsService.save(details);
                placingDetails.add(savedDetails);
            }
        });
        savedPlacing.setGames(placingDetails);

        // Warenkorb des Benutzers leeren/zurücksetzen
        cart.setTotalPrice(0);
        shoppingCartGameService.deleteAllById(cart.getGames());
        cart.getGames().removeAll(cart.getGames());

        shoppingCartService.save(cart); // Zurückgesetzen Warenkorb persistieren

        // Die Gesamtsummen aller Warenkörbe neu berechnen, da sich die Top-3 der schlechtesten Spiele
        // geändert haben könnte und Kunden sonst fälschlicherweise Rabatt bekommen
        shoppingCartService.getAllShoppingCarts()
                .forEach(shoppingCartService::calculateShoppingCartSum);

        return ResponseEntity.ok(PlacingResponseMapper.INSTANCE.entityToResponse(savedPlacing));
    }

    /**
     * Liefert alle Bestellungen zu einem Benutzer.
     *
     * @param userId - ID zum Benutzer
     * @return - Liste aller Bestellungen zum Benutzer
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<List<PlacingResponse>> getPlacementsForUser(@PathVariable("id") Long userId) {

        UserEntity user = userService.getUserById(userId);
        PersonEntity person = user.getPerson();

        List<PlacingResponse> placingResponses = placingService.getAllPlacingsByPersonId(person.getId())
                .stream()
                .map(PlacingResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(placingResponses);
    }

    /**
     * Liefert die Bestellung zur ID.
     *
     * @param placingId - ID zur Bestellung
     * @return - Bestellung zur ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlacingResponse> getPlacingById(@PathVariable("id") Long placingId) {

        PlacingEntity placing = placingService.getPlacingByPlacingId(placingId);

        return ResponseEntity.ok(PlacingResponseMapper.INSTANCE.entityToResponse(placing));
    }

    private boolean isKeysAvailable(ShoppingCartEntity cart) {
        return cart.getGames().stream().noneMatch(game ->
                keyCodeService.getKeyCountByGameIdAndSold(game.getGame().getId(), false) < game.getAmount());
    }
}
