package at.vaaniicx.lap.mapper.location;

import at.vaaniicx.lap.model.entity.LocationEntity;
import at.vaaniicx.lap.model.response.location.LocationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationResponseMapper {

    LocationResponseMapper INSTANCE = Mappers.getMapper(LocationResponseMapper.class);

    LocationResponse entityToResponse(LocationEntity source);
}
