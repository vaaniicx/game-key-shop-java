package at.vaaniicx.lap.mapper.address;

import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.model.response.address.AddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressResponseMapper {

    AddressResponseMapper INSTANCE = Mappers.getMapper(AddressResponseMapper.class);

    AddressResponse entityToResponse(AddressEntity source);
}
