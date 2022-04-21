package at.vaaniicx.lap.mapper.role;

import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.model.response.role.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleResponseMapper {

    RoleResponseMapper INSTANCE = Mappers.getMapper(RoleResponseMapper.class);

    RoleResponse entityToResponse(RoleEntity source);
}
