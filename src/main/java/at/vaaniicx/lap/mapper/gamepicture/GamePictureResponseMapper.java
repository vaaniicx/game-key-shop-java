package at.vaaniicx.lap.mapper.gamepicture;

import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.model.response.gamepicture.GamePictureResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GamePictureResponseMapper {

    GamePictureResponseMapper INSTANCE = Mappers.getMapper(GamePictureResponseMapper.class);

    GamePictureResponse entityToResponse(GamePictureEntity source);
}
