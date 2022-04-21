package at.vaaniicx.lap.mapper.user;

import at.vaaniicx.lap.mapper.person.PersonResponseMapper;
import at.vaaniicx.lap.mapper.role.RoleResponseMapper;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.response.user.UserResponse;
import at.vaaniicx.lap.util.ImageConversionHelper;

public class UserResponseMapperImpl implements UserResponseMapper {

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
        destination.setPerson(PersonResponseMapper.INSTANCE.entityToResponse(source.getPerson()));
        destination.setRole(RoleResponseMapper.INSTANCE.entityToResponse(source.getRole()));
        destination.setProfilePicture(source.getProfilePicture() != null ? ImageConversionHelper.blobToByteArray(source.getProfilePicture().getPicture()) : null);

        // TODO: PROFILBILD ALS EXTRA REQUEST SCHICKEN

        return destination;
    }
}
