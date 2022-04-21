package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.game.GameResponseMapper;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import at.vaaniicx.lap.model.request.management.game.RegisterGameRequest;
import at.vaaniicx.lap.model.response.GamePreviewResponse;
import at.vaaniicx.lap.model.response.game.GameResponse;
import at.vaaniicx.lap.service.*;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final DeveloperService developerService;
    private final PublisherService publisherService;
    private final GamePictureService gamePictureService;
    private final CategoryService categoryService;
    private final CategoryGameService categoryGameService;

    @Autowired
    public GameController(GameService gameService, DeveloperService developerService, PublisherService publisherService,
                          GamePictureService gamePictureService, CategoryService categoryService,
                          CategoryGameService categoryGameService) {
        this.gameService = gameService;
        this.developerService = developerService;
        this.publisherService = publisherService;
        this.gamePictureService = gamePictureService;
        this.categoryService = categoryService;
        this.categoryGameService = categoryGameService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<GameResponse>> getAllGames() {

        List<GameResponse> gameResponses = gameService.getAllGamesOrderByTitle()
                .stream()
                .map(GameResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(gameResponses);
    }

    @GetMapping("/preview")
    public ResponseEntity<List<GamePreviewResponse>> getGamePreviews() {

        List<GamePreviewResponse> gamePreviewResponses = gameService.getAllGamesOrderByTitle()
                .stream()
                .map(e -> {
                    GamePictureEntity thumbnail = gamePictureService.getThumbnailByGameId(e.getId());

                    return new GamePreviewResponse(e.getId(), e.getTitle(), e.getPrice(), e.getShortDescription(),
                            thumbnail.getId(), ImageConversionHelper.blobToByteArray(thumbnail.getImage()));
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(gamePreviewResponses);
    }

    @PostMapping("/register")
    public ResponseEntity<GameResponse> registerGame(@RequestBody @Validated RegisterGameRequest request) {

        // Game-Objekt erstellen und persistieren
        GameEntity gameEntity = getGameFromRequest(request);
        gameService.save(gameEntity);

        // Thumbnail-Objekt erstellen und persistieren
        GamePictureEntity thumbnail = getGamePicture(gameEntity, request.getThumbnail(), true);
        gamePictureService.save(thumbnail);

        // Spielebilder-Objekte erstellen und persistieren
        request.getGamePictures().forEach(entity -> {
            GamePictureEntity gamePicture = getGamePicture(gameEntity, entity, false);
            gamePictureService.save(gamePicture);
        });

        // Spielekategorie-Objekte erstellen und persistieren
        request.getCategories().forEach(entity -> {
            CategoryGameEntity categoryGame = getCategoryGame(gameEntity, entity);
            categoryGameService.save(categoryGame);
        });

        return ResponseEntity.ok(GameResponseMapper.INSTANCE.entityToResponse(gameEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable("id") Long gameId) {

        GameResponse gameResponse = GameResponseMapper.INSTANCE.entityToResponse(gameService.getGameById(gameId));

        return ResponseEntity.ok(gameResponse);
    }

    @DeleteMapping("/{id}/delete/category/{catId}")
    public ResponseEntity<Boolean> deleteCategoryFromGame(@PathVariable("id") Long gameId, @PathVariable("catId") Long categoryId) {

        CategoryGameEntity categoryGameById = categoryGameService.getCategoryGameById(new CategoryGamePk(categoryId, gameId));
        categoryGameService.delete(categoryGameById); // Datensatz löschen

        return ResponseEntity.ok(Boolean.TRUE);
    }

    private GameEntity getGameFromRequest(RegisterGameRequest request) {

        // Game-Objekt erstellen und mit den Daten aus dem Request befüllen
        GameEntity gameEntity = GameResponseMapper.INSTANCE.responseToEntity(request);
        // Entwickler mit der ID aus der Datenbank holen und setzen
        gameEntity.setDeveloper(developerService.getDeveloperById(request.getDeveloperId()));
        // Publisher mit der ID aus der Datenbank holen und setzen
        gameEntity.setPublisher(publisherService.getPublisherById(request.getPublisherId()));

        return gameEntity;
    }

    private GamePictureEntity getGamePicture(GameEntity gameEntity, byte[] imageBytes, boolean isThumbnail) {

        GamePictureEntity picture = new GamePictureEntity();
        picture.setGame(gameEntity);
        picture.setImage(ImageConversionHelper.byteArrayToBlob(imageBytes));
        picture.setThumb(isThumbnail);

        return picture;
    }

    private CategoryGameEntity getCategoryGame(GameEntity gameEntity, Long categoryId) {

        CategoryGameEntity categoryGame = new CategoryGameEntity();
        categoryGame.setId(new CategoryGamePk(categoryId, gameEntity.getId()));
        categoryGame.setGame(gameEntity);
        categoryGame.setCategory(categoryService.getCategoryById(categoryId));

        return categoryGame;
    }
}
