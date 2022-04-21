package at.vaaniicx.lap.mapper.location;

import at.vaaniicx.lap.mapper.country.CountryResponseMapper;
import at.vaaniicx.lap.model.entity.LocationEntity;
import at.vaaniicx.lap.model.response.location.LocationResponse;
import org.mapstruct.factory.Mappers;

public class LocationResponseMapperImpl implements LocationResponseMapper {

    private final CountryResponseMapper countryMapper = Mappers.getMapper(CountryResponseMapper.class);

    @Override
    public LocationResponse entityToResponse(LocationEntity source) {
        if (source == null) {
            return null;
        }

        LocationResponse destination = new LocationResponse();
        destination.setId(source.getId());
        destination.setCountry(countryMapper.entityToResponse(source.getCountry()));
        destination.setLocation(source.getLocation());
        destination.setPostal(source.getPostal());

        return destination;
    }
}
