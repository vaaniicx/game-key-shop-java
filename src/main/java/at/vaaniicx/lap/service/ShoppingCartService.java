package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.shoppingcart.ShoppingCartNotFoundException;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.response.game.StatisticGameResponse;
import at.vaaniicx.lap.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final GameService gameService;
    private final ShoppingCartGameService shoppingCartGameService;
    private final KeyCodeService keyCodeService;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, GameService gameService,
                               ShoppingCartGameService shoppingCartGameService, KeyCodeService keyCodeService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.gameService = gameService;
        this.shoppingCartGameService = shoppingCartGameService;
        this.keyCodeService = keyCodeService;
    }

    /**
     * Retourniert alle Warenkörbe.
     *
     * @return - Liste aller Warenkörbe
     */
    public List<ShoppingCartEntity> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    /**
     * Retourniert den zur übergebenen ID zugehörigen Warenkorb.
     *
     * @return - Warenkorb zur übergebenen ID
     */
    public ShoppingCartEntity getShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> entity = shoppingCartRepository.findById(id);

        if (!entity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        return entity.get();
    }

    /**
     * Retourniert den zur übergebenen Person-ID zugehörigen Warenkorb.
     *
     * @return - Warenkorb zur übergebenen Person-ID
     */
    public ShoppingCartEntity getShoppingCartByPersonId(Long id) {
        Optional<ShoppingCartEntity> entity = shoppingCartRepository.findByPersonId(id);

        if (!entity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        return entity.get();
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public ShoppingCartEntity save(ShoppingCartEntity entity) {
        return shoppingCartRepository.save(entity);
    }

    /**
     * Berechnet die Gesamtsumme des Warenkorbs.
     *
     * @param cart - Warenkorb, dessen Gesamtsumme berechnet werden soll
     * @return - Gesamtsumme
     */
    public double calculateShoppingCartSum(ShoppingCartEntity cart) {

        List<ShoppingCartGameEntity> allEntries = shoppingCartGameService.getShoppingCartGameByShoppingCartId(cart.getId());

        // Top 3 der schlechtesten Spiele -> 10% Rabatt
        List<Long> statisticGameIds = gameService.getAllGamesOrderByTitle().stream().map(e -> new StatisticGameResponse(e.getId(), e.getTitle(),
                        keyCodeService.getKeyCountByGameIdAndSold(e.getId(), true)))
                .sorted(Comparator.comparing(StatisticGameResponse::getKeysSold))
                .map(StatisticGameResponse::getId)
                .collect(Collectors.toList());

        statisticGameIds.subList(3, statisticGameIds.size()).clear(); // Nur die 3 schlechtesten in der Liste behalten

        // Berechnung der Gesamtsumme
        double totalPrice = 0;
        for (ShoppingCartGameEntity entry : allEntries) {
            GameEntity game = entry.getGame();

            // Sollte das Spiel weniger als 0x im Warenkorb drinnen sein, aus dem WK entfernen
            if (entry.getAmount() <= 0) {
                shoppingCartGameService.deleteAllById(Collections.singleton(entry));
                cart.getGames().remove(entry);
                continue; // Berechnung überspringen
            }

            double price = game.getPrice();
            // Spiel ist in der Top 3 enthalten -> Rabatt gewähren
            if (statisticGameIds.contains(game.getId())) {
                price *= 0.9; // -10% Rabatt
            }

            totalPrice += price * entry.getAmount();
            totalPrice = Math.round(totalPrice * 100d) / 100d; // Ergebnis auf 2 Stellen abrunden
        }
        return totalPrice;
    }
}
