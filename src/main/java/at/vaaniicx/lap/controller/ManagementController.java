package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.*;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import at.vaaniicx.lap.model.request.KeyManagementGenerateCodeRequest;
import at.vaaniicx.lap.model.request.management.category.RegisterCategoryRequest;
import at.vaaniicx.lap.model.request.management.key.RegisterCodeRequest;
import at.vaaniicx.lap.model.request.management.developer.RegisterDeveloperRequest;
import at.vaaniicx.lap.model.request.management.game.RegisterGameRequest;
import at.vaaniicx.lap.model.request.management.publisher.RegisterPublisherRequest;
import at.vaaniicx.lap.model.response.RegisterGameResponse;
import at.vaaniicx.lap.model.response.management.*;
import at.vaaniicx.lap.model.response.management.category.RegisterCategoryResponse;
import at.vaaniicx.lap.model.response.management.developer.RegisterDeveloperResponse;
import at.vaaniicx.lap.model.response.management.key.GameFlatResponse;
import at.vaaniicx.lap.model.response.management.key.GenerateCodeResponse;
import at.vaaniicx.lap.model.response.management.key.RegisterCodeResponse;
import at.vaaniicx.lap.model.response.management.placement.PlacingDetailsResponse;
import at.vaaniicx.lap.model.response.management.placement.PlacingManagementDataResponse;
import at.vaaniicx.lap.model.response.management.publisher.DataResponse;
import at.vaaniicx.lap.model.response.management.publisher.RegisterPublisherResponse;
import at.vaaniicx.lap.model.response.management.role.UserByRoleResponse;
import at.vaaniicx.lap.service.*;
import at.vaaniicx.lap.util.ImageConversionHelper;
import at.vaaniicx.lap.util.KeyCodeGenerationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryGameService categoryGameService;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PlacingService placingService;

    @Autowired
    private GameService gameService;

    @Autowired
    private KeyCodeService keyCodeService;

    @Autowired
    private GamePictureService gamePictureService;

    @GetMapping("/user")
    public List<UserManagementDataResponse> getUserManagementData() {
        List<UserManagementDataResponse> ret = new ArrayList<>();

        userService.getAllUsers().stream().filter(Objects::nonNull)
                .forEach(u -> ret.add(new UserManagementDataResponse(u.getId(), u.getEmail(), u.getRole().getId(),
                        u.getRole().getRole(), u.isActive(), u.getRegistrationDate(), u.getLastLogin())));

        return ret;
    }

    @GetMapping("/role")
    public List<RoleManagementDataResponse> getRoleManagementData() {
        List<RoleManagementDataResponse> ret = new ArrayList<>();

        roleService.getAllRoles().stream().filter(Objects::nonNull)
                .forEach(r -> ret.add(new RoleManagementDataResponse(r.getId(), r.getRole())));

        return ret;
    }

    @GetMapping("/placing")
    public ResponseEntity<List<PlacingManagementDataResponse>> getPlacingManagementData() {
        List<PlacingManagementDataResponse> ret = new ArrayList<>();

        placingService.getAllPlacings().forEach(pla -> {
            ret.add(PlacingManagementDataResponse
                    .builder()
                    .placingId(pla.getId())
                    .placingDate(pla.getPlacingDate())
                    .totalPrice(pla.getTotalPrice())
                    .personId(pla.getPerson().getId())
                    .placingDetails(pla.getGames().stream().map(det -> PlacingDetailsResponse
                            .builder()
                            .placingId(det.getPlacing().getId())
                            .title(det.getKeyCode().getGame().getTitle())
                            .ageRestriction(det.getKeyCode().getGame().getAgeRestriction())
                            .keyId(det.getKeyCode().getId())
                            .keyCode(det.getKeyCode().getKeyCode())
                            .gameId(det.getKeyCode().getGame().getId())
                            .build()).collect(Collectors.toList()))
                    .build());
        });

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/role/{id}/user")
    public List<UserByRoleResponse> getUserByRole(@PathVariable("id") Long roleId) {
        return userService.findUserByRoleId(roleId).stream()
                .map(u -> UserByRoleResponse.builder().id(u.getId()).email(u.getEmail()).active(u.isActive()).build())
                .collect(Collectors.toList());
    }

    @GetMapping("/address")
    public List<AddressManagementDataResponse> getAddressManagementData() {
        List<AddressManagementDataResponse> ret = new ArrayList<>();

        addressService.getAllAddresses().stream().filter(Objects::nonNull)
                .forEach(a -> ret.add(new AddressManagementDataResponse(a.getId(), a.getStreet(), a.getHouseNumber(),
                        a.getStair(), a.getDoor(), a.getLocation().getId(), a.getLocation().getLocation(),
                        a.getLocation().getPostal(), a.getLocation().getCountry().getId(),
                        a.getLocation().getCountry().getCountry())));

        return ret;
    }

    @GetMapping("/category")
    public List<CategoryManagementDataResponse> getCategoryManagementData() {
        List<CategoryManagementDataResponse> ret = new ArrayList<>();

        categoryService.getAllCategories().stream().filter(Objects::nonNull)
                .forEach(c ->
                        ret.add(new CategoryManagementDataResponse(c.getId(), c.getCategory(), c.getDescription())));

        return ret;
    }

    @GetMapping("/developer")
    public List<at.vaaniicx.lap.model.response.management.developer.DataResponse> getDeveloperManagementData() {
        List<at.vaaniicx.lap.model.response.management.developer.DataResponse> ret = new ArrayList<>();

        developerService.getAllDeveloper().stream().filter(Objects::nonNull)
                .forEach(d -> ret.add(new at.vaaniicx.lap.model.response.management.developer.DataResponse(d.getId(), d.getDeveloper())));

        return ret;
    }

    @GetMapping("/publisher")
    public List<DataResponse> getPublisherManagementData() {
        List<DataResponse> ret = new ArrayList<>();

        publisherService.getAllPublisher().stream().filter(Objects::nonNull)
                .forEach(p -> ret.add(new DataResponse(p.getId(), p.getPublisher())));

        return ret;
    }

    @GetMapping("/game")
    public List<GameManagementDataResponse> getGameManagementData() {
        List<GameManagementDataResponse> ret = new ArrayList<>();

        gameService.getAllGames().stream().filter(Objects::nonNull)
                .forEach(g -> ret.add(new GameManagementDataResponse(g.getId(), g.getTitle(), g.getDeveloper().getId(),
                        g.getDeveloper().getDeveloper(), g.getPublisher().getId(), g.getPublisher().getPublisher(),
                        g.getReleaseDate(), g.getOriginalPrice(), g.getPrice(), g.getAgeRestriction())));

        return ret;
    }

    @GetMapping("/game/flat/{id}")
    public GameFlatResponse getKeyManagementGameFlat(@PathVariable("id") Long gameId) {
        return new GameFlatResponse(gameId, gameService.getGameById(gameId).getTitle(),
                keyCodeService.getKeyCountByGameIdAndSold(gameId, true), keyCodeService.getKeyCountByGameIdAndSold(gameId, false));
    }

    @GetMapping("/game/key/{id}")
    public List<at.vaaniicx.lap.model.response.management.key.DataResponse> getKeyManagementData(@PathVariable("id") Long gameId) {
        List<at.vaaniicx.lap.model.response.management.key.DataResponse> ret = new ArrayList<>();

        keyCodeService.getAllKeyCodesByGameId(gameId).stream().filter(Objects::nonNull)
                .forEach(k -> {
                    UserEntity user = null;
                    if (k.getPerson() != null) {
                        user = userService.getUserByPersonId(k.getPerson().getId());
                    }

                    ret.add(new at.vaaniicx.lap.model.response.management.key.DataResponse(k.getId(), k.getKeyCode(), k.isSold(),
                            user != null ? user.getId() : null,
                            user != null ? user.getEmail() : null));
                });

        return ret;
    }

    @PostMapping("/game/key/generate")
    public List<GenerateCodeResponse> generateKeys(@RequestBody @Validated KeyManagementGenerateCodeRequest request) {
        List<GenerateCodeResponse> ret = new ArrayList<>();

        for (byte i = 0; i < request.getAmount(); i++) {
            String keyCode = KeyCodeGenerationHelper.generateKeyCode();

            KeyCodeEntity entity =
                    keyCodeService.saveKeyCode(new KeyCodeEntity(gameService.getGameById(request.getGameId()), keyCode, false));

            ret.add(new GenerateCodeResponse(entity.getId(), entity.getKeyCode()));
        }

        return ret;
    }

    @PostMapping("/game/key/register")
    public RegisterCodeResponse registerCode(@RequestBody @Validated RegisterCodeRequest request) {
        KeyCodeEntity entity = keyCodeService.saveKeyCode(
                new KeyCodeEntity(gameService.getGameById(request.getGameId()), request.getKeyCode(), false));

        return new RegisterCodeResponse(entity.getGame().getId(), entity.getId(), entity.getKeyCode());
    }

    @DeleteMapping("/game/key/delete/{id}")
    public ResponseEntity<Boolean> deleteKey(@PathVariable("id") Long keyId) {
        boolean deleted = keyCodeService.deleteKeyCodeById(keyId);

        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/warehouse/entrance")
    public List<WarehouseEntranceDataResponse> getWarehouseEntranceData() {
        List<WarehouseEntranceDataResponse> ret = new ArrayList<>();

        gameService.getAllGamesOrderByTitle().stream().filter(Objects::nonNull).forEach(g -> {
            Long amount = keyCodeService.getKeyCountByGameIdAndSold(g.getId(), false);
            ret.add(new WarehouseEntranceDataResponse(g.getId(), g.getTitle(), amount, g.getPrice()));
        });

        return ret;
    }

    @PostMapping("/game/register")
    public RegisterGameResponse registerGame(@RequestBody @Validated RegisterGameRequest request) {
        // GameEntity speichern
        GameEntity gameEntity = gameService.registerGame(GameEntity.builder().title(request.getTitle())
                .description(request.getDescription()).shortDescription(request.getShortDescription())
                .releaseDate(request.getReleaseDate()).originalPrice(request.getOriginalPrice())
                .price(request.getPrice())
                .savings(BigDecimal.valueOf(request.getOriginalPrice()).subtract(BigDecimal.valueOf(request.getPrice())).doubleValue())
                .systemRequirements("")
                .developer(developerService.getDeveloperById(request.getDeveloperId()))
                .publisher(publisherService.getPublisherById(request.getPublisherId()))
                .ageRestriction(request.getAgeRestriction())
                .build());

        // Thumbnail speichern
        gamePictureService.save(GamePictureEntity.builder().game(gameEntity)
                .image(ImageConversionHelper.byteArrayToBlob(request.getThumbnail())).thumb(true).build());

        // Spielebilder speichern
        request.getGamePictures().forEach(gp -> gamePictureService.save(GamePictureEntity.builder().game(gameEntity)
                .image(ImageConversionHelper.byteArrayToBlob(gp)).thumb(false).build()));

        // Kategorien speichern
        request.getCategories().forEach(cat ->
                categoryGameService.save(CategoryGameEntity.builder()
                        .id(new CategoryGamePk(cat, gameEntity.getId()))
                        .game(gameEntity)
                        .category(categoryService.getCategoryById(cat))
                        .build()));

        return new RegisterGameResponse(gameEntity.getId(), gameEntity.getTitle(), gameEntity.getReleaseDate(),
                gameEntity.getDeveloper().getId(), gameEntity.getPublisher().getId(), request.getAgeRestriction());
    }

    @PostMapping("/developer/register")
    public RegisterDeveloperResponse registerDeveloper(@RequestBody @Validated RegisterDeveloperRequest request) {
        DeveloperEntity developerEntity = developerService.registerDeveloper(request.getDeveloper());

        return new RegisterDeveloperResponse(developerEntity.getId(), developerEntity.getDeveloper());
    }

    @PostMapping("/publisher/register")
    public RegisterPublisherResponse registerPublisher(@RequestBody @Validated RegisterPublisherRequest request) {
        PublisherEntity publisherEntity = publisherService.registerPublisher(request.getPublisher());

        return new RegisterPublisherResponse(publisherEntity.getId(), publisherEntity.getPublisher());
    }

    @PostMapping("/category/register")
    public RegisterCategoryResponse registerCategory(@RequestBody @Validated RegisterCategoryRequest request) {
        CategoryEntity categoryEntity = categoryService.registerCategory(request.getCategory(), request.getDescription());

        return new RegisterCategoryResponse(categoryEntity.getId(), categoryEntity.getCategory(), categoryEntity.getDescription());
    }
}
