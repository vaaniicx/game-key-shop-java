package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.CountryDTO;
import at.vaaniicx.lap.model.entity.CountryEntity;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public static CountryDTO toDto(CountryEntity entity) {
        return new CountryDTO(entity.getId(), entity.getCountry());
    }

}
