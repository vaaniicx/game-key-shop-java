package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.PlacingDTO;
import at.vaaniicx.lap.model.entity.PlacingEntity;

import java.util.stream.Collectors;

public class PlacingMapper {

    public static PlacingDTO toDto(PlacingEntity e) {
        return new PlacingDTO(e.getId(), e.getTotalPrice(), e.getPlacingDate(),
                e.getPlacingDetails().stream().map(PlacingDetailsMapper::toDto).collect(Collectors.toList()));
    }
}
