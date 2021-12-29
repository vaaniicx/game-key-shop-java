package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.UserDTO;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDTO toDto(UserEntity e) {
        return new UserDTO(e.getId(), e.getEmail(), e.isActive(), e.getRegistrationDate(), e.getLastLogin(),
                PersonMapper.toDto(e.getPerson()), RoleMapper.toDto(e.getRole()),
                e.getProfilePicture() != null ?
                        ImageConversionHelper.blobToByteArray(e.getProfilePicture().getPicture()) : null);
    }
}
