package at.vaaniicx.lap.mapper.gamepicture;

import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.model.response.gamepicture.GamePictureResponse;
import org.mapstruct.Mapper;

@Mapper
public interface GamePictureResponseMapper {

    GamePictureResponse entityToResponse(GamePictureEntity source);
}
