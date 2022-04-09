package at.vaaniicx.lap.mapper.profilepicture;

import at.vaaniicx.lap.model.entity.ProfilePictureEntity;
import at.vaaniicx.lap.model.response.profilepicture.ProfilePictureResponse;
import at.vaaniicx.lap.util.ImageConversionHelper;

public class ProfilePictureResponseMapperImpl implements ProfilePictureResponseMapper {

    @Override
    public ProfilePictureResponse entityToResponse(ProfilePictureEntity source) {
        if (source == null) {
            return null;
        }

        ProfilePictureResponse destination = new ProfilePictureResponse();
        destination.setId(source.getId());
        destination.setPicture(ImageConversionHelper.blobToByteArray(source.getPicture()));

        return destination;
    }
}
