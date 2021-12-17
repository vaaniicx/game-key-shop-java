package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.model.request.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressEntity toEntity(RegisterRequest request) {
        return new AddressEntity(request.getStreet(), request.getHouseNumber(), request.getDoor(), request.getStair());
    }
}
