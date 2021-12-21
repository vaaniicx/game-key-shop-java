package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.GamePictureDTO;
import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.stereotype.Component;

@Component
public class GamePictureMapper {

    public GamePictureDTO toDto(GamePictureEntity entity) {
        return new GamePictureDTO(entity.getId(), entity.getGame().getId(),
                ImageConversionHelper.blobToByteArray(entity.getImage()), entity.isThumb());
    }
}
