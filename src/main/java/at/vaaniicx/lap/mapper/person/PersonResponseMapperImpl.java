package at.vaaniicx.lap.mapper.person;

import at.vaaniicx.lap.mapper.address.AddressResponseMapper;
import at.vaaniicx.lap.mapper.placing.PlacingResponseMapper;
import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.model.response.person.PersonResponse;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

public class PersonResponseMapperImpl implements PersonResponseMapper {

    private AddressResponseMapper addressMapper = Mappers.getMapper(AddressResponseMapper.class);
    private PlacingResponseMapper placingMapper = Mappers.getMapper(PlacingResponseMapper.class);

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
        destination.setAddress(addressMapper.entityToResponse(source.getAddress()));
        destination.setPlacings(source.getPlacings().stream().map(e -> placingMapper.entityToResponse(e)).collect(Collectors.toList()));

        return destination;
    }
}
