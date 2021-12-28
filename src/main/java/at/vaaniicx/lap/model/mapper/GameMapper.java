package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.GameDTO;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.service.CategoryGameService;
import at.vaaniicx.lap.service.GamePictureService;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    private static GamePictureService gamePictureService;
    private static CategoryGameService categoryGameService;

    @Autowired
    private GamePictureService gamePictureService0;

    @Autowired
    private CategoryGameService categoryGameService0;

    @PostConstruct
    private void initStaticFields() {
        gamePictureService = this.gamePictureService0;
        categoryGameService = this.categoryGameService0;
    }

    public static GameDTO toDto(GameEntity e) {
        return new GameDTO(e.getId(), e.getTitle(), e.getDescription(), e.getShortDescription(),
                e.getReleaseDate(), e.getOriginalPrice(), e.getPrice(), e.getSavings(),
                e.getSystemRequirements(), DeveloperMapper.toDto(e.getDeveloper()), PublisherMapper.toDto(e.getPublisher()),
                getGamePicturesForGameByGameId(e.getId()), e.getAgeRestriction(),
                getThumbnailForGameByGameId(e.getId()), categoryGameService.getCategoriesByGameId(e.getId()).stream().map(v -> CategoryMapper.toDto(v.getCategory())).collect(Collectors.toList()));
    }

    private static List<byte[]> getGamePicturesForGameByGameId(Long id) {
        return gamePictureService.getGamePicturesForGameId(id).stream()
                .filter(e -> !e.isThumb())
                .map(e -> ImageConversionHelper.blobToByteArray(e.getImage())).collect(Collectors.toList());
    }

    private static byte[] getThumbnailForGameByGameId(Long id) {
        return ImageConversionHelper.blobToByteArray(gamePictureService.getThumbPictureForGameId(id).getImage());
    }
}

