package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.DeveloperDTO;
import at.vaaniicx.lap.model.entity.DeveloperEntity;
import org.springframework.stereotype.Component;

@Component
public class DeveloperMapper {

    public static DeveloperDTO toDto(DeveloperEntity entity) {
        return new DeveloperDTO(entity.getId(), entity.getDeveloper());
    }
}
