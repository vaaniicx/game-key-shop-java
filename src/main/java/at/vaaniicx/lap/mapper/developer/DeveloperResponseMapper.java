package at.vaaniicx.lap.mapper.developer;

import at.vaaniicx.lap.model.entity.DeveloperEntity;
import at.vaaniicx.lap.model.response.developer.DeveloperResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeveloperResponseMapper {

    DeveloperResponseMapper INSTANCE = Mappers.getMapper(DeveloperResponseMapper.class);

    DeveloperResponse entityToResponse(DeveloperEntity source);
}
