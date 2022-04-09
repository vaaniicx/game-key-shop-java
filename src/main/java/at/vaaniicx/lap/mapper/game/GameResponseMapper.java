package at.vaaniicx.lap.mapper.game;

import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.response.game.GameResponse;
import org.mapstruct.Mapper;

@Mapper
public interface GameResponseMapper {

    GameResponse entityToResponse(GameEntity source);
}
