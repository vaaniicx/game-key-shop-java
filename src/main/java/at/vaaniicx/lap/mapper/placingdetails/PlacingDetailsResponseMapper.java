package at.vaaniicx.lap.mapper.placingdetails;

import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.model.response.placingdetails.PlacingDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlacingDetailsResponseMapper {

    PlacingDetailsResponseMapper INSTANCE = Mappers.getMapper(PlacingDetailsResponseMapper.class);

    PlacingDetailsResponse entityToResponse(PlacingDetailsEntity source);
}
