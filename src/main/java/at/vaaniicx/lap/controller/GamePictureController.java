package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.gamepicture.GamePictureResponseMapper;
import at.vaaniicx.lap.model.response.gamepicture.GamePictureResponse;
import at.vaaniicx.lap.service.GamePictureService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gamepicture")
public class GamePictureController {

    @Autowired
    private GamePictureService gamePictureService;

    private GamePictureResponseMapper gamePictureMapper = Mappers.getMapper(GamePictureResponseMapper.class);

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
