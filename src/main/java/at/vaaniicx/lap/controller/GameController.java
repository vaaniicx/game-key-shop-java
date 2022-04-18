package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.game.GameResponseMapper;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import at.vaaniicx.lap.model.response.game.GameResponse;
import at.vaaniicx.lap.service.CategoryGameService;
import at.vaaniicx.lap.service.GameService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final CategoryGameService categoryGameService;
    private final GameResponseMapper gameMapper;

    public GameController(GameService gameService, CategoryGameService categoryGameService) {
        this.gameService = gameService;
        this.categoryGameService = categoryGameService;
        this.gameMapper = Mappers.getMapper(GameResponseMapper.class);
    }

    @GetMapping
    @ResponseBody
    public List<GameResponse> getAll() {
        return gameService.getAllGamesOrderByTitle()
                .stream()
                .map(gameMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GameResponse getById(@PathVariable("id") Long gameId) {
        return gameMapper.entityToResponse(gameService.getGameById(gameId));
    }

    @DeleteMapping("/{id}/remove/category/{catId}")
    public ResponseEntity<Boolean> removeCategoryFromGame(@PathVariable("id") Long gameId, @PathVariable("catId") Long categoryId) {

        CategoryGameEntity e = categoryGameService.getCategoryGameById(new CategoryGamePk(categoryId, gameId));
        categoryGameService.delete(e);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
