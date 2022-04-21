package at.vaaniicx.lap.mapper.key;

import at.vaaniicx.lap.mapper.game.GameResponseMapper;
import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.model.response.key.KeyResponse;

public class KeyResponseMapperImpl implements KeyResponseMapper {

    @Override
    public KeyResponse entityToResponse(KeyCodeEntity source) {
        if (source == null) {
            return null;
        }

        KeyResponse destination = new KeyResponse();
        destination.setId(source.getId());
        destination.setGame(GameResponseMapper.INSTANCE.entityToResponse(source.getGame()));
        destination.setPersonId(source.getPerson().getId());
        destination.setKeyCode(source.getKeyCode());
        destination.setSold(source.isSold());

        return destination;
    }
}
