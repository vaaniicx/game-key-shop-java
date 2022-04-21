package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.gamepicture.GamePictureResponseMapper;
import at.vaaniicx.lap.model.response.gamepicture.GamePictureResponse;
import at.vaaniicx.lap.service.GamePictureService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gamepicture")
public class GamePictureController {

    private GamePictureService gamePictureService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<GamePictureResponse>> getAll() {

        List<GamePictureResponse> gamePictureResponses = gamePictureService.getAllGamePictures()
                .stream()
                .map(GamePictureResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(gamePictureResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamePictureResponse> getById(@PathVariable("id") Long id) {

        GamePictureResponse gamePictureResponse = GamePictureResponseMapper.INSTANCE.entityToResponse(gamePictureService.getGamePictureById(id));

        return ResponseEntity.ok(gamePictureResponse);
    }
}
