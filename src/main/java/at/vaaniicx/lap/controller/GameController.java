package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.GameDTO;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import at.vaaniicx.lap.model.mapper.GameMapper;
import at.vaaniicx.lap.service.CategoryGameService;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.KeyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private KeyCodeService keyCodeService;

    @Autowired
    private CategoryGameService categoryGameService;

    @GetMapping
    @ResponseBody
    public List<GameDTO> getAll() {
        return gameService.getAllGamesOrderByTitle()
                .stream()
                .map(GameMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GameDTO getById(@PathVariable("id") Long gameId) {
        return GameMapper.toDto(gameService.getGameById(gameId));
    }

    @DeleteMapping("/{id}/remove/category/{catId}")
    public ResponseEntity<Boolean> removeCategoryFromGame(@PathVariable("id") Long gameId, @PathVariable("catId") Long categoryId) {

        CategoryGameEntity e = categoryGameService.getCategoryGameById(new CategoryGamePk(categoryId, gameId));
        categoryGameService.delete(e);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
