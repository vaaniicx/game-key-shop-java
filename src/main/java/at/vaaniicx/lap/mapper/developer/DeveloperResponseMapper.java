package at.vaaniicx.lap.mapper.developer;

import at.vaaniicx.lap.model.entity.DeveloperEntity;
import at.vaaniicx.lap.model.response.developer.DeveloperResponse;
import org.mapstruct.Mapper;

@Mapper
public interface DeveloperResponseMapper {

    DeveloperResponse entityToResponse(DeveloperEntity source);
}
