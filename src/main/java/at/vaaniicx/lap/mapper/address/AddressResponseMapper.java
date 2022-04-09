package at.vaaniicx.lap.mapper.address;

import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.model.response.address.AddressResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AddressResponseMapper {

    AddressResponse entityToResponse(AddressEntity source);
}
