package at.vaaniicx.lap.mapper.key;

import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.model.response.key.KeyResponse;
import org.mapstruct.Mapper;

@Mapper
public interface KeyResponseMapper {

    KeyResponse entityToResponse(KeyCodeEntity source);
}
