package at.vaaniicx.lap.mapper.country;

import at.vaaniicx.lap.model.entity.CountryEntity;
import at.vaaniicx.lap.model.response.country.CountryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryResponseMapper {

    CountryResponseMapper INSTANCE = Mappers.getMapper(CountryResponseMapper.class);

    CountryResponse entityToResponse(CountryEntity source);
}
