package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.game.DeleteGameException;
import at.vaaniicx.lap.mapper.game.GameResponseMapper;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import at.vaaniicx.lap.model.entity.pk.ShoppingCartGamePk;
import at.vaaniicx.lap.model.request.game.RegisterGameRequest;
import at.vaaniicx.lap.model.request.game.UpdateGameRequest;
import at.vaaniicx.lap.model.response.GamePreviewResponse;
import at.vaaniicx.lap.model.response.game.GameResponse;
import at.vaaniicx.lap.model.response.game.PopularGameResponse;
import at.vaaniicx.lap.model.response.game.SlimGameResponse;
import at.vaaniicx.lap.model.response.game.StatisticGameResponse;
import at.vaaniicx.lap.service.*;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashSet;
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
    private final KeyCodeService keyCodeService;
    private final PlacingService placingService;
    private final ShoppingCartGameService shoppingCartGameService;

    @Autowired
    public GameController(GameService gameService, DeveloperService developerService, PublisherService publisherService,
                          GamePictureService gamePictureService, CategoryService categoryService,
                          CategoryGameService categoryGameService, KeyCodeService keyCodeService,
                          PlacingService placingService, ShoppingCartGameService shoppingCartGameService) {
        this.gameService = gameService;
        this.developerService = developerService;
        this.publisherService = publisherService;
        this.gamePictureService = gamePictureService;
        this.categoryService = categoryService;
        this.categoryGameService = categoryGameService;
        this.keyCodeService = keyCodeService;
        this.placingService = placingService;
        this.shoppingCartGameService = shoppingCartGameService;
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

    @GetMapping("/statistic")
    public ResponseEntity<List<StatisticGameResponse>> getStatisticGames() {

        List<StatisticGameResponse> responses = gameService.getAllGamesOrderByTitle().stream().map(e -> new StatisticGameResponse(e.getId(), e.getTitle(),
                        keyCodeService.getKeyCountByGameIdAndSold(e.getId(), true)))
                .sorted(Comparator.comparing(StatisticGameResponse::getKeysSold))
                .collect(Collectors.toList());

        responses.subList(3, responses.size()).clear();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/preview")
    public ResponseEntity<List<GamePreviewResponse>> getGamePreviews() {

        List<GamePreviewResponse> gamePreviewResponses = gameService.getAllGamesOrderByTitle()
                .stream()
                .map(e -> {
                    GamePictureEntity thumbnail = gamePictureService.getThumbnailByGameId(e.getId());

                    return new GamePreviewResponse(e.getId(), e.getTitle(), e.getPrice(), e.getShortDescription(),
                            thumbnail.getId(), ImageConversionHelper.blobToByteArray(thumbnail.getImage()),
                            keyCodeService.getKeyCountByGameIdAndSold(e.getId(), false));
                }).collect(Collectors.toList());

        return ResponseEntity.ok(gamePreviewResponses);
    }

    @GetMapping("/warehouse")
    public ResponseEntity<List<SlimGameResponse>> getWarehouse() {

        List<SlimGameResponse> responses = gameService.getAllGamesOrderByTitle()
                .stream()
                .map(g -> {
                    SlimGameResponse slimGameResponse = GameResponseMapper.INSTANCE.entityToSlimResponse(g);
                    slimGameResponse.setKeysAvail(keyCodeService.getKeyCountByGameIdAndSold(g.getId(), false));

                    return slimGameResponse;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/register")
    public ResponseEntity<GameResponse> registerGame(@RequestBody @Validated RegisterGameRequest request) {

        // Game-Objekt erstellen und persistieren
        GameEntity gameEntity = createGameFromRequest(request);
        gameEntity.setGamePictures(new HashSet<>());
        gameEntity.setCategories(new HashSet<>());
        gameService.save(gameEntity);

        // Thumbnail-Objekt erstellen und persistieren
        GamePictureEntity thumbnail = getGamePicture(gameEntity, request.getThumbnail(), true);
        gameEntity.getGamePictures().add(thumbnail);
        gamePictureService.save(thumbnail);

        // Spielebilder-Objekte erstellen und persistieren
        request.getGamePictures().forEach(entity -> {
            GamePictureEntity gamePicture = getGamePicture(gameEntity, entity, false);
            gameEntity.getGamePictures().add(gamePicture);
            gamePictureService.save(gamePicture);
        });

        // Spielekategorie-Objekte erstellen und persistieren
        request.getCategories().forEach(entity -> {
            CategoryGameEntity categoryGame = getCategoryGame(gameEntity, entity);
            categoryGameService.save(categoryGame);
        });

        return ResponseEntity.ok(GameResponseMapper.INSTANCE.entityToResponse(gameEntity));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateGame(@RequestBody @Validated UpdateGameRequest request) {

        // Game-Objekt erstellen und neue Werte setzen
        GameEntity gameEntity = gameService.getGameById(request.getId());
        gameEntity.setTitle(request.getTitle());
        gameEntity.setDescription(request.getDescription());
        gameEntity.setShortDescription(request.getShortDescription());
        gameEntity.setReleaseDate(request.getReleaseDate());
        gameEntity.setOriginalPrice(request.getOriginalPrice());
        gameEntity.setPrice(request.getPrice());
        gameEntity.setSystemRequirements(request.getSystemRequirements());
        gameEntity.setAgeRestriction(request.getAgeRestriction());
        // Entwickler mit der ID aus der Datenbank holen und setzen
        gameEntity.setDeveloper(developerService.getDeveloperById(request.getDeveloperId()));
        // Publisher mit der ID aus der Datenbank holen und setzen
        gameEntity.setPublisher(publisherService.getPublisherById(request.getPublisherId()));

        // Kategorien und Spielebilder löschen
        categoryGameService.deleteAll(gameEntity.getCategories());
        gameEntity.getCategories().removeAll(gameEntity.getCategories());

        gamePictureService.deleteAll(gameEntity.getGamePictures());
        gameEntity.getGamePictures().removeAll(gameEntity.getGamePictures());

        // Persistieren
        gameService.save(gameEntity);

        // Thumbnail-Objekt erstellen und persistieren
        GamePictureEntity thumbnail = getGamePicture(gameEntity, request.getThumbnail(), true);
        gameEntity.getGamePictures().add(thumbnail);
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

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteGameById(@PathVariable("id") Long id) {

        GameEntity gameById = gameService.getGameById(id);

        boolean isGameSet = placingService.getAllPlacings()
                .stream()
                .anyMatch(placing -> placing.getGames().stream().map(entry -> entry.getKeyCode().getGame().getId())
                        .collect(Collectors.toList())
                        .contains(id));

        if (!isGameSet) {
            categoryGameService.deleteAll(gameById.getCategories());
            gamePictureService.deleteAll(gameById.getGamePictures());
            keyCodeService.deleteAll(gameById.getKeys());
            shoppingCartGameService.deleteAllByGameId(gameById.getId());
            gameService.deleteById(id);
            // refetch warenkörbe, games
        } else {
            throw new DeleteGameException();
        }

        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable("id") Long gameId) {

        GameResponse gameResponse = GameResponseMapper.INSTANCE.entityToResponse(gameService.getGameById(gameId));

        return ResponseEntity.ok(gameResponse);
    }

    @PutMapping("/{id}/remove/category/{catId}")
    public ResponseEntity<Boolean> deleteCategoryFromGame(@PathVariable("id") Long gameId, @PathVariable("catId") Long categoryId) {

        CategoryGameEntity categoryGameById = categoryGameService.getCategoryGameById(new CategoryGamePk(categoryId, gameId));
        categoryGameService.delete(categoryGameById); // Datensatz löschen

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/slim/{id}")
    public ResponseEntity<SlimGameResponse> getSlimGameResponse(@PathVariable("id") Long id) {

        SlimGameResponse slimGameResponse = GameResponseMapper.INSTANCE.entityToSlimResponse(gameService.getGameById(id));
        slimGameResponse.setKeysAvail(keyCodeService.getKeyCountByGameIdAndSold(id, false));
        slimGameResponse.setKeysSold(keyCodeService.getKeyCountByGameIdAndSold(id, true));

        return ResponseEntity.ok(slimGameResponse);
    }

    private GameEntity createGameFromRequest(RegisterGameRequest request) {

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
