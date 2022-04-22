package at.vaaniicx.lap.mapper.person;

import at.vaaniicx.lap.mapper.address.AddressResponseMapper;
import at.vaaniicx.lap.mapper.placing.PlacingResponseMapper;
import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.model.response.person.PersonResponse;

import java.util.stream.Collectors;

public class PersonResponseMapperImpl implements PersonResponseMapper {

    @Override
    public PersonResponse entityToResponse(PersonEntity source) {
        if (source == null) {
            return null;
        }

        PersonResponse destination = new PersonResponse();
        destination.setId(source.getId());
        destination.setFirstName(source.getFirstName());
        destination.setLastName(source.getLastName());
        destination.setBirthDate(source.getBirthDate());
        destination.setAddress(AddressResponseMapper.INSTANCE.entityToResponse(source.getAddress()));
        destination.setPlacings(source.getPlacings()
                .stream()
                .map(PlacingResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList())
        );

        return destination;
    }
}
