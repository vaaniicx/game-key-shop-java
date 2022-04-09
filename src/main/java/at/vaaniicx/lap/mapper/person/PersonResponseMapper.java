package at.vaaniicx.lap.mapper.person;

import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.model.response.person.PersonResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PersonResponseMapper {

    PersonResponse entityToResponse(PersonEntity source);
}
