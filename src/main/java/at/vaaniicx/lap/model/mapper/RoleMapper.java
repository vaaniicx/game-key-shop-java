package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.RoleDTO;
import at.vaaniicx.lap.model.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public static RoleDTO toDto(RoleEntity entity) {
        return new RoleDTO(entity.getId(), entity.getRole());
    }
}
