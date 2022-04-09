package at.vaaniicx.lap.mapper.location;

import at.vaaniicx.lap.model.entity.LocationEntity;
import at.vaaniicx.lap.model.response.location.LocationResponse;
import org.mapstruct.Mapper;

@Mapper
public interface LocationResponseMapper {

    LocationResponse entityToResponse(LocationEntity source);
}
