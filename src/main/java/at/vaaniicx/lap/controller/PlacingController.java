package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.*;
import at.vaaniicx.lap.model.entity.pk.PlacingDetailsPk;
import at.vaaniicx.lap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/placing")
public class PlacingController {

    @Autowired
    private PlacingService placingService;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PlacingDetailsService placingDetailsService;

    @Autowired
    private KeyCodeService keyCodeService;

    @GetMapping("/{id}/create")
    public ResponseEntity<PlacingEntity> createPlacingForUser(@PathVariable("id") Long userId) {

        UserEntity user = userService.getUserById(userId);
        PersonEntity person = user.getPerson();
        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(person.getId());

        // Check for available keys
        AtomicBoolean available = new AtomicBoolean(true);
        cart.getGames().forEach(game -> {
            byte amount = game.getAmount();
            Long keysAvailable = keyCodeService.getKeyCountByGameIdAndSold(game.getGame().getId(), false);

            if (keysAvailable < amount) {
                available.set(false);
            }
        });

        if (!available.get()) {
            return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        }

        // Create placing
        PlacingEntity placing = new PlacingEntity();
        placing.setPlacingDate(Date.from(Instant.now()));
        placing.setTotalPrice(cart.getTotalPrice());
        placing.setPerson(person);

        // Save placing in DB
        PlacingEntity savedPlacing = placingService.save(placing);

        // Create placing details
        List<PlacingDetailsEntity> placingDetails = new ArrayList<>();

        cart.getGames().forEach(item -> {

            List<KeyCodeEntity> availableKeyCodes = keyCodeService.getAllAvailableKeyCodesByGameId(item.getGame().getId());

            for (int i = 0; i < item.getAmount(); i++) {
                PlacingDetailsEntity details = new PlacingDetailsEntity();

                KeyCodeEntity keyCode = availableKeyCodes.get(i);
                keyCode.setPerson(person);
                keyCode.setSold(true);

                // Persist key
                KeyCodeEntity savedKeyCode = keyCodeService.saveKeyCode(keyCode);

                // Set details
                details.setId(new PlacingDetailsPk(savedPlacing.getId(), savedKeyCode.getId()));
                details.setPlacing(savedPlacing);
                details.setPrice(savedKeyCode.getGame().getPrice());
                details.setKeyCode(savedKeyCode);

                // Persist details
                PlacingDetailsEntity savedDetails = placingDetailsService.save(details);

                // adding to list
                placingDetails.add(savedDetails);
            }
        });

        return new ResponseEntity(placing, HttpStatus.OK);
    }
}
