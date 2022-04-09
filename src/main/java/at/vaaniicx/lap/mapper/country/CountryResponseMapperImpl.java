package at.vaaniicx.lap.mapper.country;

import at.vaaniicx.lap.model.entity.CountryEntity;
import at.vaaniicx.lap.model.response.country.CountryResponse;

public class CountryResponseMapperImpl implements CountryResponseMapper {

    @Override
    public CountryResponse entityToResponse(CountryEntity source) {
        if (source == null) {
            return null;
        }

        CountryResponse destination = new CountryResponse();
        destination.setId(source.getId());
        destination.setCountry(source.getCountry());

        return destination;
    }
}
