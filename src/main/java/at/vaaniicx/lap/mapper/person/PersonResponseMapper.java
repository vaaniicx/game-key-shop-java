package at.vaaniicx.lap.mapper.person;

import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.model.response.person.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonResponseMapper {

    PersonResponseMapper INSTANCE = Mappers.getMapper(PersonResponseMapper.class);

    PersonResponse entityToResponse(PersonEntity source);
}
