package at.vaaniicx.lap.mapper.placingdetails;

import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.model.response.placingdetails.PlacingDetailsResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PlacingDetailsResponseMapper {

    PlacingDetailsResponse entityToResponse(PlacingDetailsEntity source);
}
