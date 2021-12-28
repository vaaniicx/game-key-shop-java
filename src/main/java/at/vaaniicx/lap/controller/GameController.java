package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.GameDTO;
import at.vaaniicx.lap.model.mapper.GameMapper;
import at.vaaniicx.lap.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    @ResponseBody
    public List<GameDTO> getAll() {
        return gameService.getAllGamesOrderByTitle().stream().map(GameMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GameDTO getById(@PathVariable Long id) {
        return GameMapper.toDto(gameService.getGameById(id));
    }
}
