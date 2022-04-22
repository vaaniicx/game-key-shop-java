package at.vaaniicx.lap.mapper.placingdetails;

import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.model.response.placingdetails.PlacingDetailsResponse;

public class PlacingDetailsResponseMapperImpl implements PlacingDetailsResponseMapper {

    @Override
    public PlacingDetailsResponse entityToResponse(PlacingDetailsEntity source) {
        if (source == null) {
            return null;
        }

        PlacingDetailsResponse destination = new PlacingDetailsResponse();
        destination.setPlacingId(source.getPlacing().getId());
        destination.setTitle(source.getKeyCode().getGame().getTitle());
        destination.setAgeRestriction(source.getKeyCode().getGame().getAgeRestriction());
        destination.setKeyId(source.getKeyCode().getId());
        destination.setKeyCode(source.getKeyCode().getKeyCode());
        destination.setGameId(source.getKeyCode().getGame().getId());

        return destination;
    }
}