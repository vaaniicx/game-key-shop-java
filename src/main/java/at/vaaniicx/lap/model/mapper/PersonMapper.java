package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.model.request.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonEntity toEntity(RegisterRequest request) {
        return new PersonEntity(request.getFirstName(), request.getLastName(), request.getBirthDate());
    }
}
