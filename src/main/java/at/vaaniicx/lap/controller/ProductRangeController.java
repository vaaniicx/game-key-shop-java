package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.model.response.GamePreviewDataResponse;
import at.vaaniicx.lap.service.GamePictureService;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/productrange")
public class ProductRangeController {

    private final GameService gameService;
    private final GamePictureService gamePictureService;

    public ProductRangeController(GameService gameService, GamePictureService gamePictureService) {
        this.gameService = gameService;
        this.gamePictureService = gamePictureService;
    }

    @GetMapping("/preview")
    public List<GamePreviewDataResponse> getGamePreviewData() {
        List<GamePreviewDataResponse> ret = new ArrayList<>();

        gameService.getAllGamesOrderByTitle().stream().filter(Objects::nonNull)
                .forEach(g -> {
                    GamePictureEntity thumbnail = gamePictureService.getThumbPictureForGameId(g.getId());

                    ret.add(new GamePreviewDataResponse(g.getId(), g.getTitle(), g.getPrice(), g.getShortDescription(),
                            thumbnail.getId(), ImageConversionHelper.blobToByteArray(thumbnail.getImage())));
                });

        return ret;
    }
}
