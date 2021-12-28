package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.KeyCodeDTO;
import at.vaaniicx.lap.model.entity.KeyCodeEntity;

public class KeyCodeMapper {

    public static KeyCodeDTO toDto(KeyCodeEntity e){
        return new KeyCodeDTO(e.getId(), GameMapper.toDto(e.getGame()), PersonMapper.toDto(e.getPerson()),
                e.getKeyCode(), e.isSold());
    }
}
