package at.vaaniicx.lap.mapper.key;

import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.model.response.key.KeyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KeyResponseMapper {

    KeyResponseMapper INSTANCE = Mappers.getMapper(KeyResponseMapper.class);

    KeyResponse entityToResponse(KeyCodeEntity source);
}
