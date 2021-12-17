package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.entity.LocationEntity;
import at.vaaniicx.lap.model.request.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationEntity toEntity(RegisterRequest request) {
        return new LocationEntity(request.getPostal(), request.getLocation());
    }
}
