package at.vaaniicx.lap.mapper.role;

import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.model.response.role.RoleResponse;
import org.mapstruct.Mapper;

@Mapper
public interface RoleResponseMapper {

    RoleResponse entityToResponse(RoleEntity source);
}
