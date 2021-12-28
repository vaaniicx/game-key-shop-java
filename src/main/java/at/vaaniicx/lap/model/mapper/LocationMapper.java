package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.LocationDTO;
import at.vaaniicx.lap.model.entity.LocationEntity;
import at.vaaniicx.lap.model.request.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public static LocationEntity toEntity(RegisterRequest request) {
        return new LocationEntity(request.getPostal(), request.getLocation());
    }

    public static LocationDTO toDto(LocationEntity e) {
        return new LocationDTO(e.getId(), e.getPostal(), e.getLocation(), CountryMapper.toDto(e.getCountry()));
    }
}
