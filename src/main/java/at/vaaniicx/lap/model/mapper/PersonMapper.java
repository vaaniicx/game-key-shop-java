package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.PersonDTO;
import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.model.request.RegisterRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public static PersonEntity toEntity(RegisterRequest request) {
        return new PersonEntity(request.getFirstName(), request.getLastName(), request.getBirthDate());
    }

    public static PersonDTO toDto(PersonEntity e) {
        return new PersonDTO(e.getId(), e.getFirstName(), e.getLastName(), e.getBirthDate(),
                AddressMapper.toDto(e.getAddress()),
                e.getPlacings().stream().map(PlacingMapper::toDto).collect(Collectors.toList()));
    }
}
