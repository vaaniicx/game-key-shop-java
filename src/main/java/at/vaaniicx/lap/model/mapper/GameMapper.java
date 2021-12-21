package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.GameDTO;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.service.GamePictureService;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    @Autowired
    private GamePictureService gamePictureService;

    public GameDTO toDto(GameEntity entity) {
        return new GameDTO(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getShortDescription(),
                entity.getReleaseDate(), entity.getOriginalPrice(), entity.getPrice(), entity.getSavings(),
                entity.getSystemRequirements(), entity.getDeveloper().getId(), entity.getPublisher().getId(),
                getGamePicturesForGameByGameId(entity.getId()), entity.getAgeRestriction(),
                getThumbnailForGameByGameId(entity.getId()));
    }

    private List<byte[]> getGamePicturesForGameByGameId(Long id) {
        return gamePictureService.getGamePicturesForGameId(id).stream()
                .filter(e -> !e.isThumb())
                .map(e -> ImageConversionHelper.blobToByteArray(e.getImage())).collect(Collectors.toList());
    }

    private byte[] getThumbnailForGameByGameId(Long id) {
        return ImageConversionHelper.blobToByteArray(gamePictureService.getThumbPictureForGameId(id).getImage());
    }
}

