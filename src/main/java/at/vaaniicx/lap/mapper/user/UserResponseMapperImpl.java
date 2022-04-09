package at.vaaniicx.lap.mapper.user;

import at.vaaniicx.lap.mapper.person.PersonResponseMapper;
import at.vaaniicx.lap.mapper.role.RoleResponseMapper;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.response.user.UserResponse;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.mapstruct.factory.Mappers;

public class UserResponseMapperImpl implements UserResponseMapper {

    private PersonResponseMapper personMapper = Mappers.getMapper(PersonResponseMapper.class);
    private RoleResponseMapper roleMapper = Mappers.getMapper(RoleResponseMapper.class);

    @Override
    public UserResponse entityToResponse(UserEntity source) {
        if (source == null) {
            return null;
        }

        UserResponse destination = new UserResponse();
        destination.setId(source.getId());
        destination.setEmail(source.getEmail());
        destination.setActive(source.isActive());
        destination.setRegistrationDate(source.getRegistrationDate());
        destination.setLastLogin(source.getLastLogin());
        destination.setPerson(personMapper.entityToResponse(source.getPerson()));
        destination.setRole(roleMapper.entityToResponse(source.getRole()));
        destination.setProfilePicture(ImageConversionHelper.blobToByteArray(source.getProfilePicture().getPicture()));

        return destination;
    }
}
