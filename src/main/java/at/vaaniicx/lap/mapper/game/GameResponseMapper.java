package at.vaaniicx.lap.mapper.game;

import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.request.game.RegisterGameRequest;
import at.vaaniicx.lap.model.request.game.UpdateGameRequest;
import at.vaaniicx.lap.model.response.game.GameResponse;
import at.vaaniicx.lap.model.response.game.SlimGameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameResponseMapper {

    GameResponseMapper INSTANCE = Mappers.getMapper(GameResponseMapper.class);

    GameResponse entityToResponse(GameEntity source);

    SlimGameResponse entityToSlimResponse(GameEntity source);

    GameEntity responseToEntity(RegisterGameRequest source);

    GameEntity responseToEntity(UpdateGameRequest source);
}
