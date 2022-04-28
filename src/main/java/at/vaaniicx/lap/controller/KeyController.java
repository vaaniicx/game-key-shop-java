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

    @GetMapping("/{id}")
    public ResponseEntity<SlimKeyResponse> getKeyById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(KeyResponseMapper.INSTANCE.entityToSlimResponse(keyCodeService.getKeyCodeById(id)));
    }

    @PostMapping("/update")
    public ResponseEntity<SlimKeyResponse> updateKeyCode(@RequestBody @Validated UpdateKeyCodeRequest request) {

        KeyCodeEntity keyCode = keyCodeService.getKeyCodeById(request.getId());
        keyCode.setKeyCode(request.getKeyCode());

        KeyCodeEntity persistedKeyCode = keyCodeService.save(keyCode);

        return ResponseEntity.ok(KeyResponseMapper.INSTANCE.entityToSlimResponse(persistedKeyCode));
    }

    @PostMapping("/register")
    public ResponseEntity<SlimKeyResponse> registerKeyCode(@RequestBody @Validated RegisterKeyCodeRequest request) {

        KeyCodeEntity entity = keyCodeService.save(new KeyCodeEntity(
                gameService.getGameById(request.getGameId()), request.getKeyCode(), false));

        return ResponseEntity.ok(KeyResponseMapper.INSTANCE.entityToSlimResponse(entity));
    }

    @PostMapping("/generate")
    public ResponseEntity<List<SlimKeyResponse>> generateKeyCodesForGame(@RequestBody @Validated GenerateKeyCodeRequest request) {

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
    public ResponseEntity<Boolean> deleteKeyCode(@PathVariable("id") Long id) {

        keyCodeService.deleteById(id);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<List<SlimKeyResponse>> getKeyCodesByGameId(@PathVariable("id") Long id) {

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
