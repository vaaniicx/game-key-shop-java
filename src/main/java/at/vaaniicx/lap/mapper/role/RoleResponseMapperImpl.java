package at.vaaniicx.lap.mapper.role;

import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.model.response.role.RoleResponse;

public class RoleResponseMapperImpl implements RoleResponseMapper {

    @Override
    public RoleResponse entityToResponse(RoleEntity source) {
        if (source == null) {
            return null;
        }

        RoleResponse destination = new RoleResponse();
        destination.setId(source.getId());
        destination.setRole(source.getRole());

        return destination;
    }
}
