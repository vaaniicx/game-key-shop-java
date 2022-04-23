package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.user.UserNotFoundException;
import at.vaaniicx.lap.mapper.key.KeyResponseMapper;
import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.request.KeyManagementGenerateCodeRequest;
import at.vaaniicx.lap.model.request.management.key.RegisterCodeRequest;
import at.vaaniicx.lap.model.response.key.SlimKeyResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.KeyCodeService;
import at.vaaniicx.lap.service.UserService;
import at.vaaniicx.lap.util.KeyCodeGenerationHelper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/register")
    public ResponseEntity<SlimKeyResponse> registerCode(@RequestBody @Validated RegisterCodeRequest request) {

        KeyCodeEntity entity = keyCodeService.save(new KeyCodeEntity(
                gameService.getGameById(request.getGameId()), request.getKeyCode(), false));

        return ResponseEntity.ok(KeyResponseMapper.INSTANCE.entityToSlimResponse(entity));
    }

    @PostMapping("/generate")
    public ResponseEntity<List<SlimKeyResponse>> generateKeysForGame(@RequestBody @Validated KeyManagementGenerateCodeRequest request) {

        List<SlimKeyResponse> codeResponses = new ArrayList<>();

        for (byte i = 0; i < request.getAmount(); i++) {
            String keyCode = KeyCodeGenerationHelper.generateKeyCode();

            KeyCodeEntity entity =
                    keyCodeService.save(new KeyCodeEntity(gameService.getGameById(request.getGameId()), keyCode, false));

            codeResponses.add(KeyResponseMapper.INSTANCE.entityToSlimResponse(entity));
        }

        return ResponseEntity.ok(codeResponses);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteKey(@PathVariable("id") Long id) {

        keyCodeService.deleteById(id);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<List<SlimKeyResponse>> getKeysByGameId(@PathVariable("id") Long id) {

        List<SlimKeyResponse> response = keyCodeService.getAllKeyCodesByGameId(id)
                .stream()
                .map(e -> {
                    SlimKeyResponse slimKeyResponse = KeyResponseMapper.INSTANCE.entityToSlimResponse(e);

                    if (e.getPerson() == null) {
                        return slimKeyResponse;
                    }

                    try {
                        UserEntity user = userService.getUserByPersonId(e.getPerson().getId());
                        slimKeyResponse.setUserId(user.getId());
                        slimKeyResponse.setEmail(user.getEmail());

                        return slimKeyResponse;
                    } catch (UserNotFoundException exc) {
                        return slimKeyResponse;
                    }
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
