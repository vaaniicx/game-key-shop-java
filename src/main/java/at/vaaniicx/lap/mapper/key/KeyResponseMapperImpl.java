package at.vaaniicx.lap.mapper.key;

import at.vaaniicx.lap.mapper.game.GameResponseMapper;
import at.vaaniicx.lap.mapper.person.PersonResponseMapper;
import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.model.response.key.KeyResponse;
import org.mapstruct.factory.Mappers;

public class KeyResponseMapperImpl implements KeyResponseMapper {

    private GameResponseMapper gameMapper = Mappers.getMapper(GameResponseMapper.class);
    private PersonResponseMapper personMapper = Mappers.getMapper(PersonResponseMapper.class);

    @Override
    public KeyResponse entityToResponse(KeyCodeEntity source) {
        if (source == null) {
            return null;
        }

        KeyResponse destination = new KeyResponse();
        destination.setId(source.getId());
        destination.setGame(gameMapper.entityToResponse(source.getGame()));
        destination.setPerson(personMapper.entityToResponse(source.getPerson()));
        destination.setKeyCode(source.getKeyCode());
        destination.setSold(source.isSold());

        return destination;
    }
}
