package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.model.request.KeyManagementGenerateCodeRequest;
import at.vaaniicx.lap.model.request.management.key.RegisterCodeRequest;
import at.vaaniicx.lap.model.response.key.GenerateCodeResponse;
import at.vaaniicx.lap.model.response.key.RegisterCodeResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.KeyCodeService;
import at.vaaniicx.lap.util.KeyCodeGenerationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/key")
public class KeyController {

    private final KeyCodeService keyCodeService;
    private final GameService gameService;

    @Autowired
    public KeyController(KeyCodeService keyCodeService, GameService gameService) {
        this.keyCodeService = keyCodeService;
        this.gameService = gameService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterCodeResponse> registerCode(@RequestBody @Validated RegisterCodeRequest request) {

        KeyCodeEntity entity = keyCodeService.save(
                new KeyCodeEntity(gameService.getGameById(request.getGameId()), request.getKeyCode(), false));

        return ResponseEntity.ok(new RegisterCodeResponse(entity.getGame().getId(), entity.getId(), entity.getKeyCode()));
    }

    @PostMapping("/generate")
    public ResponseEntity<List<GenerateCodeResponse>> generateKeysForGame(@RequestBody @Validated KeyManagementGenerateCodeRequest request) {

        List<GenerateCodeResponse> codeResponses = new ArrayList<>();

        for (byte i = 0; i < request.getAmount(); i++) {
            String keyCode = KeyCodeGenerationHelper.generateKeyCode();

            KeyCodeEntity entity =
                    keyCodeService.save(new KeyCodeEntity(gameService.getGameById(request.getGameId()), keyCode, false));

            codeResponses.add(new GenerateCodeResponse(entity.getId(), entity.getKeyCode()));
        }

        return ResponseEntity.ok(codeResponses);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteKey(@PathVariable("id") Long id) {

        keyCodeService.deleteById(id);

        return ResponseEntity.ok(Boolean.TRUE);
    }
}
