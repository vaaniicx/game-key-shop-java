package at.vaaniicx.lap.mapper.placing;

import at.vaaniicx.lap.model.entity.PlacingEntity;
import at.vaaniicx.lap.model.response.placing.PlacingResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PlacingResponseMapper {

    PlacingResponse entityToResponse(PlacingEntity source);
}
