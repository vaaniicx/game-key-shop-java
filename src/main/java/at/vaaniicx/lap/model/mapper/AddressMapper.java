package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.AddressDTO;
import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.model.request.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public static AddressDTO toDto(AddressEntity e) {
        return new AddressDTO(e.getId(), LocationMapper.toDto(e.getLocation()), e.getStreet(), e.getHouseNumber(), e.getDoor(), e.getStair());
    }
}
