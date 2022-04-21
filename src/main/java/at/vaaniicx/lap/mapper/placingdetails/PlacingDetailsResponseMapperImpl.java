package at.vaaniicx.lap.mapper.placingdetails;

import at.vaaniicx.lap.mapper.key.KeyResponseMapper;
import at.vaaniicx.lap.mapper.placing.PlacingResponseMapper;
import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.model.response.placingdetails.PlacingDetailsResponse;
import org.mapstruct.factory.Mappers;

public class PlacingDetailsResponseMapperImpl implements PlacingDetailsResponseMapper {

    @Override
    public PlacingDetailsResponse entityToResponse(PlacingDetailsEntity source) {
        if (source == null) {
            return null;
        }

        PlacingDetailsResponse destination = new PlacingDetailsResponse();
        destination.setPlacingId(source.getPlacing().getId());
        destination.setKeyCode(KeyResponseMapper.INSTANCE.entityToResponse(source.getKeyCode()));
        destination.setPrice(source.getPrice());

        return destination;
    }
}