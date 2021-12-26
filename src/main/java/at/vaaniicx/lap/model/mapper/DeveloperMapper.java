package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.DeveloperDTO;
import at.vaaniicx.lap.model.dto.RoleDTO;
import at.vaaniicx.lap.model.entity.DeveloperEntity;
import at.vaaniicx.lap.model.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class DeveloperMapper {

    public DeveloperDTO toDto(DeveloperEntity entity) {
        return new DeveloperDTO(entity.getId(), entity.getDeveloper());
    }
}
