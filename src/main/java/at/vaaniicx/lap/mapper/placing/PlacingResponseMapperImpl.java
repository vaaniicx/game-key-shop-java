package at.vaaniicx.lap.mapper.placing;

import at.vaaniicx.lap.mapper.placingdetails.PlacingDetailsResponseMapper;
import at.vaaniicx.lap.model.entity.PlacingEntity;
import at.vaaniicx.lap.model.response.placing.PlacingResponse;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

public class PlacingResponseMapperImpl implements PlacingResponseMapper {

    @Override
    public PlacingResponse entityToResponse(PlacingEntity source) {
        if (source == null) {
            return null;
        }

        PlacingResponse destination = new PlacingResponse();
        destination.setId(source.getId());
        destination.setPlacingDate(source.getPlacingDate());
        destination.setTotalPrice(source.getTotalPrice());
        destination.setPlacingDetailsResponses(source.getGames().stream().map(PlacingDetailsResponseMapper.INSTANCE::entityToResponse).collect(Collectors.toList()));

        return destination;
    }
}
