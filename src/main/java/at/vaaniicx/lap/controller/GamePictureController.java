package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.gamepicture.GamePictureResponseMapper;
import at.vaaniicx.lap.model.response.gamepicture.GamePictureResponse;
import at.vaaniicx.lap.service.GamePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gamepicture")
public class GamePictureController {

    private final GamePictureService gamePictureService;

    @Autowired
    public GamePictureController(GamePictureService gamePictureService) {
        this.gamePictureService = gamePictureService;
    }

    /**
     * Liefert das Spielebild zur ID.
     *
     * @param id - ID zum Spielebild
     * @return - Spielebild zur ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<GamePictureResponse> getById(@PathVariable("id") Long id) {

        GamePictureResponse gamePictureResponse =
                GamePictureResponseMapper.INSTANCE.entityToResponse(gamePictureService.getGamePictureById(id));

        return ResponseEntity.ok(gamePictureResponse);
    }
}
