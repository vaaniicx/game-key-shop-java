package at.vaaniicx.lap.mapper.address;

import at.vaaniicx.lap.mapper.location.LocationResponseMapper;
import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.model.response.address.AddressResponse;

public class AddressResponseMapperImpl implements AddressResponseMapper {

    @Override
    public AddressResponse entityToResponse(AddressEntity source) {
        if (source == null) {
            return null;
        }

        AddressResponse destination = new AddressResponse();
        destination.setId(source.getId());
        destination.setLocation(LocationResponseMapper.INSTANCE.entityToResponse(source.getLocation()));
        destination.setStreet(source.getStreet());
        destination.setHouseNumber(source.getHouseNumber());
        destination.setDoor(source.getDoor());
        destination.setStair(source.getStair());

        return destination;
    }
}
