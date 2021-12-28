package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.PlacingDetailsDTO;
import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;

public class PlacingDetailsMapper {

    public static PlacingDetailsDTO toDto(PlacingDetailsEntity e) {
        return new PlacingDetailsDTO(PlacingMapper.toDto(e.getPlacing()), GameMapper.toDto(e.getGame()),
                KeyCodeMapper.toDto(e.getKeyCode()), e.getPrice());
    }
}
