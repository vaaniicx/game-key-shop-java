package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.gamepicture.GamePictureResponseMapper;
import at.vaaniicx.lap.model.response.gamepicture.GamePictureResponse;
import at.vaaniicx.lap.service.GamePictureService;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gamepicture")
public class GamePictureController {

    private final GamePictureService gamePictureService;
    private final GamePictureResponseMapper gamePictureMapper;

    public GamePictureController(GamePictureService gamePictureService) {
        this.gamePictureService = gamePictureService;
        this.gamePictureMapper = Mappers.getMapper(GamePictureResponseMapper.class);
    }

    @GetMapping
    @ResponseBody
    public List<GamePictureResponse> getAll() {
        return gamePictureService.getAllGamePictures().stream().map(gamePictureMapper::entityToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GamePictureResponse getById(@PathVariable("id") Long gamePictureId) {
        return gamePictureMapper.entityToResponse(gamePictureService.getGamePictureById(gamePictureId));
    }
}
