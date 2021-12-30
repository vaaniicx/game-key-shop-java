package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.GamePictureDTO;
import at.vaaniicx.lap.model.mapper.GamePictureMapper;
import at.vaaniicx.lap.service.GamePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gamepicture")
public class GamePictureController {

    @Autowired
    private GamePictureService gamePictureService;

    @GetMapping
    @ResponseBody
    public List<GamePictureDTO> getAll() {
        return gamePictureService.getAllGamePictures().stream().map(GamePictureMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GamePictureDTO getById(@PathVariable("id") Long gamePictureId) {
        return GamePictureMapper.toDto(gamePictureService.getGamePictureById(gamePictureId));
    }
}
