package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.user.UserNotFoundException;
import at.vaaniicx.lap.mapper.key.KeyResponseMapper;
import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.request.key.GenerateKeyCodeRequest;
import at.vaaniicx.lap.model.request.key.UpdateKeyCodeRequest;
import at.vaaniicx.lap.model.request.key.RegisterKeyCodeRequest;
import at.vaaniicx.lap.model.response.key.SlimKeyResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.KeyCodeService;
import at.vaaniicx.lap.service.UserService;
import at.vaaniicx.lap.util.KeyCodeGenerationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/key")
public class KeyController {

    private final KeyCodeService keyCodeService;
    private final GameService gameService;
    private final UserService userService;

    @Autowired
    public KeyController(KeyCodeService keyCodeService, GameService gameService, UserService userService) {
        this.keyCodeService = keyCodeService;
        this.gameService = gameService;
        this.userService = userService;
    }

    /**
     * Liefert den Schlüssel zur ID.
     *
     * @param id - ID zum Schlüssel
     * @return - Reduziertes Schlüssel-Objekt
     */
    @GetMapping("/{id}")
    public ResponseEntity<SlimKeyResponse> getKeyById(@PathVariable("id") Long id) {

        SlimKeyResponse response = KeyResponseMapper.INSTANCE.entityToSlimResponse(keyCodeService.getKeyCodeById(id));

        return ResponseEntity.ok(response);
    }

    /**
     * Aktualisiert einen Schlüssel zur ID.
     *
     * @param request - Update-Request
     * @return - Aktualisierter Schlüssel
     */
    @PostMapping("/update")
    public ResponseEntity<SlimKeyResponse> updateKeyCode(@RequestBody @Validated UpdateKeyCodeRequest request) {

        KeyCodeEntity keyCode = keyCodeService.getKeyCodeById(request.getId());
        keyCode.setKeyCode(request.getKeyCode()); // Neue Werte setzen

        KeyCodeEntity persistedKeyCode = keyCodeService.save(keyCode); // Persistieren

        return ResponseEntity.ok(KeyResponseMapper.INSTANCE.entityToSlimResponse(persistedKeyCode));
    }

    /**
     * Registriert einen Schlüssel.
     *
     * @param request - Registrierungs-Request
     * @return - Registrierten Schlüssel
     */
    @PostMapping("/register")
    public ResponseEntity<SlimKeyResponse> registerKeyCode(@RequestBody @Validated RegisterKeyCodeRequest request) {

        KeyCodeEntity newKeyCode = new KeyCodeEntity(
                gameService.getGameById(request.getGameId()), request.getKeyCode(), false); // Erstellen

        KeyCodeEntity persistedKeyCode = keyCodeService.save(newKeyCode); // Persistieren

        return ResponseEntity.ok(KeyResponseMapper.INSTANCE.entityToSlimResponse(persistedKeyCode));
    }

    /**
     * Generiert eine beliebige Anzahl an KeyCodes für ein bestimmtes Spiel.
     *
     * @param request - Generierungs-Request
     * @return - Liste der generierten KeyCodes
     */
    @PostMapping("/generate")
    public ResponseEntity<List<SlimKeyResponse>> generateKeyCodesForGame(@RequestBody @Validated GenerateKeyCodeRequest request) {

        List<SlimKeyResponse> codeResponses = new ArrayList<>();

        for (byte i = 0; i < request.getAmount(); i++) {
            String keyCode = KeyCodeGenerationHelper.generateKeyCode(); // Code generieren

            KeyCodeEntity newKeyCode =
                    new KeyCodeEntity(gameService.getGameById(request.getGameId()), keyCode, false); // Erstellen
            KeyCodeEntity entity = keyCodeService.save(newKeyCode); // Persistieren

            codeResponses.add(KeyResponseMapper.INSTANCE.entityToSlimResponse(entity));
        }

        return ResponseEntity.ok(codeResponses);
    }

    /**
     * Löscht den KeyCode zur ID.
     *
     * @param id - ID zum KeyCode
     * @return - Response-Entity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteKeyCode(@PathVariable("id") Long id) {

        keyCodeService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Liefert alle KeyCodes zu einem Spiel.
     *
     * @param id - ID zum Spiel
     * @return - Liste aller KeyCodes zu einem Spiel
     */
    @GetMapping("/game/{id}")
    public ResponseEntity<List<SlimKeyResponse>> getKeyCodesByGameId(@PathVariable("id") Long id) {

        List<SlimKeyResponse> response = keyCodeService.getAllKeyCodesByGameId(id)
                .stream()
                .map(e -> {
                    // Erstelle reduziertes Schlüssel-Objekt
                    SlimKeyResponse slimKeyResponse = KeyResponseMapper.INSTANCE.entityToSlimResponse(e);

                    // Ist der Schlüssel einer Person zugewiesen?
                    if (e.getPerson() == null) {
                        return slimKeyResponse; // Keine Zuweisung, Objekt genügt aus
                    }

                    try {
                        // Personendaten setzen
                        UserEntity user = userService.getUserByPersonId(e.getPerson().getId());
                        slimKeyResponse.setUserId(user.getId());
                        slimKeyResponse.setEmail(user.getEmail());

                        return slimKeyResponse;
                    } catch (UserNotFoundException exc) {
                        return slimKeyResponse; // Fehler ist aufgetreten
                    }
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
