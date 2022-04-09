package at.vaaniicx.lap.mapper.placingdetails;

import at.vaaniicx.lap.mapper.key.KeyResponseMapper;
import at.vaaniicx.lap.mapper.placing.PlacingResponseMapper;
import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.model.response.placingdetails.PlacingDetailsResponse;
import org.mapstruct.factory.Mappers;

public class PlacingDetailsResponseMapperImpl implements PlacingDetailsResponseMapper {

    private PlacingResponseMapper placingMapper = Mappers.getMapper(PlacingResponseMapper.class);
    private KeyResponseMapper keyMapper = Mappers.getMapper(KeyResponseMapper.class);

    @Override
    public PlacingDetailsResponse entityToResponse(PlacingDetailsEntity source) {
        if (source == null) {
            return null;
        }

        PlacingDetailsResponse destination = new PlacingDetailsResponse();
        destination.setPlacing(placingMapper.entityToResponse(source.getPlacing()));
        destination.setKeyCode(keyMapper.entityToResponse(source.getKeyCode()));
        destination.setPrice(source.getPrice());

        return destination;
    }
}
