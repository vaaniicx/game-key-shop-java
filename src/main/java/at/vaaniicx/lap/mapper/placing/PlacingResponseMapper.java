package at.vaaniicx.lap.mapper.placing;

import at.vaaniicx.lap.model.entity.PlacingEntity;
import at.vaaniicx.lap.model.response.placing.PlacingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlacingResponseMapper {

    PlacingResponseMapper INSTANCE = Mappers.getMapper(PlacingResponseMapper.class);

    PlacingResponse entityToResponse(PlacingEntity source);
}
