package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.*;
import at.vaaniicx.lap.model.entity.pk.PlacingDetailsPk;
import at.vaaniicx.lap.model.response.management.placement.CreatePlacingResponse;
import at.vaaniicx.lap.model.response.management.placement.PlacingDetailsResponse;
import at.vaaniicx.lap.model.response.management.placement.PlacingManagementDataResponse;
import at.vaaniicx.lap.model.response.management.placement.UserPlacingsResponse;
import at.vaaniicx.lap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private ShoppingCartGameService shoppingCartGameService;

    @Autowired
    private PlacingDetailsService placingDetailsService;

    @Autowired
    private KeyCodeService keyCodeService;

    @GetMapping("/{id}/create")
    public ResponseEntity<CreatePlacingResponse> createPlacingForUser(@PathVariable("id") Long userId) {

        UserEntity user = userService.getUserById(userId);
        PersonEntity person = user.getPerson();
        ShoppingCartEntity cart = shoppingCartService.getShoppingCartByPersonId(person.getId());

        // Check for available keys
        boolean available = cart.getGames().stream().noneMatch(game ->
                keyCodeService.getKeyCountByGameIdAndSold(game.getGame().getId(), false) < game.getAmount());

        if (!available) {
            // no keys for selling available
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        // Create placing
        PlacingEntity placing = new PlacingEntity();
        placing.setPlacingDate(Instant.now());
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

        // create response
        CreatePlacingResponse response =
                CreatePlacingResponse
                        .builder()
                        .placingId(savedPlacing.getId())
                        .placingDate(savedPlacing.getPlacingDate())
                        .personId(person.getId())
                        .totalPrice(cart.getTotalPrice())
                        .placingDetails(placingDetails.stream().map(details ->
                                        PlacingDetailsResponse
                                                .builder()
                                                .placingId(details.getPlacing().getId())
                                                .title(details.getKeyCode().getGame().getTitle())
                                                .ageRestriction(details.getKeyCode().getGame().getAgeRestriction())
                                                .keyId(details.getKeyCode().getId())
                                                .keyCode(details.getKeyCode().getKeyCode())
                                                .gameId(details.getKeyCode().getGame().getId())
                                                .build())
                                .collect(Collectors.toList()))
                        .build();

        // clear shoppingcart
        cart.setTotalPrice(0);
        shoppingCartGameService.deleteAllById(cart.getGames());
        cart.getGames().removeAll(cart.getGames());
        shoppingCartService.saveShoppingCart(cart);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserPlacingsResponse>> getPlacementsForUser(@PathVariable("id") Long userId) {

        UserEntity user = userService.getUserById(userId);
        PersonEntity person = user.getPerson();

        List<PlacingEntity> placings = placingService.getAllPlacingsByPersonId(person.getId());

        List<UserPlacingsResponse> ret = new ArrayList<>();
        placings.forEach(pla -> ret.add(
                UserPlacingsResponse
                        .builder()
                        .placingId(pla.getId())
                        .placingDate(pla.getPlacingDate())
                        .totalPrice(pla.getTotalPrice())
                        .placingDetails(pla.getGames().stream().map(det ->
                                PlacingDetailsResponse
                                        .builder()
                                        .placingId(det.getPlacing().getId())
                                        .title(det.getKeyCode().getGame().getTitle())
                                        .ageRestriction(det.getKeyCode().getGame().getAgeRestriction())
                                        .keyId(det.getKeyCode().getId())
                                        .keyCode(det.getKeyCode().getKeyCode())
                                        .gameId(det.getKeyCode().getGame().getId())
                                        .build()).collect(Collectors.toList()))
                        .build()));

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlacingManagementDataResponse> getPlacingDetailsForPlacingId(@PathVariable("id") Long placingId) {

        PlacingEntity placing = placingService.getPlacingByPlacingId(placingId);

        PlacingManagementDataResponse response = PlacingManagementDataResponse
                .builder()
                .placingId(placing.getId())
                .placingDate(placing.getPlacingDate())
                .totalPrice(placing.getTotalPrice())
                .personId(placing.getPerson().getId())
                .placingDetails(placing.getGames().stream().map(det -> PlacingDetailsResponse
                        .builder()
                        .placingId(det.getPlacing().getId())
                        .title(det.getKeyCode().getGame().getTitle())
                        .ageRestriction(det.getKeyCode().getGame().getAgeRestriction())
                        .keyId(det.getKeyCode().getId())
                        .keyCode(det.getKeyCode().getKeyCode())
                        .gameId(det.getKeyCode().getGame().getId())
                        .build()).collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
