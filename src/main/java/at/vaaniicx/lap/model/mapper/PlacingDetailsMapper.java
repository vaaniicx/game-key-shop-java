package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.PlacingDetailsDTO;
import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.service.CategoryGameService;
import at.vaaniicx.lap.service.GamePictureService;
import at.vaaniicx.lap.service.KeyCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class PlacingDetailsMapper {

    private static KeyCodeService keyCodeService;

    @Autowired
    private KeyCodeService keyCodeService0;

    @PostConstruct
    private void initStaticFields() {
        keyCodeService = this.keyCodeService0;
    }

    public static PlacingDetailsDTO toDto(PlacingDetailsEntity e) {
        return new PlacingDetailsDTO();
    }
}
