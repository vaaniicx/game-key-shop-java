package at.vaaniicx.lap.mapper.user;

import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.response.user.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserResponseMapper {

    UserResponse entityToResponse(UserEntity source);
}
