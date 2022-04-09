package at.vaaniicx.lap.mapper.gamepicture;

import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.model.response.gamepicture.GamePictureResponse;
import at.vaaniicx.lap.util.ImageConversionHelper;

public class GamePictureResponseMapperImpl implements GamePictureResponseMapper {

    @Override
    public GamePictureResponse entityToResponse(GamePictureEntity source) {
        if (source == null) {
            return null;
        }

        GamePictureResponse destination = new GamePictureResponse();
        destination.setId(source.getId());
        destination.setGameId(source.getGame().getId());
        destination.setImage(ImageConversionHelper.blobToByteArray(source.getImage()));
        destination.setThumb(source.isThumb());

        return destination;
    }
}
