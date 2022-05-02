package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.key.NoKeysAvailableException;
import at.vaaniicx.lap.mapper.placing.PlacingResponseMapper;
import at.vaaniicx.lap.model.entity.*;
import at.vaaniicx.lap.model.entity.pk.PlacingDetailsPk;
import at.vaaniicx.lap.model.response.game.StatisticGameResponse;
import at.vaaniicx.lap.model.response.placing.PlacingResponse;
import at.vaaniicx.lap.service.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<PlacingResponse>> getAllPlacings() {

        List<PlacingResponse> placingResponses = placingService.getAllPlacings()
                .stream()
                .map(PlacingResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(placingResponses);
    }

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
            // Es gibt nicht für alle Spiele genügend Schlüssel
            throw new NoKeysAvailableException();
        }

        // Bestellungs-Objekt erstellen und befüllen
        PlacingEntity placing = new PlacingEntity();
        placing.setPlacingDate(Instant.now());
        placing.setTotalPrice(cart.getTotalPrice());
        placing.setPerson(person);

        // Bestellung in der Datenbank persistieren
        PlacingEntity savedPlacing = placingService.save(placing);

        // Create placing details
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

            // Anzahl der bestellten Schlüssel kaufen
            for (int i = 0; i < item.getAmount(); i++) {
                // Schlüssel-Objekt befüllen
                KeyCodeEntity keyCode = availableKeyCodes.get(i);
                keyCode.setPerson(person);
                keyCode.setSold(true);

                // Aktualisiertes Schlüssel-Objekt persistieren (-> Schlüssel wurde nun an Benutzer X verkauft)
                KeyCodeEntity savedKeyCode = keyCodeService.save(keyCode);

                // Bestellungsdetails-Objekt erstellen und befüllen
                PlacingDetailsEntity details = new PlacingDetailsEntity();
                details.setId(new PlacingDetailsPk(savedPlacing.getId(), savedKeyCode.getId()));
                details.setPlacing(savedPlacing);

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

        // Zurückgesetzen Warenkorb persistieren
        shoppingCartService.save(cart);

        // Die Gesamtsummen aller Warenkörbe neu berechnen, da sich die Top-3 der schlechtesten Spiele
        // geändert haben könnte und Kunden sonst fälschlicherweise Rabatt bekommen
        shoppingCartService.getAllShoppingCarts()
                .forEach(shoppingCartService::calculateShoppingCartSum);

        return ResponseEntity.ok(PlacingResponseMapper.INSTANCE.entityToResponse(savedPlacing));
    }

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
