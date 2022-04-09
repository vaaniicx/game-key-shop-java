package at.vaaniicx.lap.mapper.country;

import at.vaaniicx.lap.model.entity.CountryEntity;
import at.vaaniicx.lap.model.response.country.CountryResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CountryResponseMapper {

    CountryResponse entityToResponse(CountryEntity source);
}
