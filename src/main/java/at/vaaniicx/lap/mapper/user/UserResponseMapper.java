package at.vaaniicx.lap.mapper.user;

import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.response.user.SlimUserResponse;
import at.vaaniicx.lap.model.response.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserResponseMapper {

    UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

    UserResponse entityToResponse(UserEntity source);

    SlimUserResponse entityToSlimResponse(UserEntity source);
}
