package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.ProfilePictureDTO;
import at.vaaniicx.lap.model.entity.ProfilePictureEntity;
import at.vaaniicx.lap.util.ImageConversionHelper;

public class ProfilePictureMapper {

    public static ProfilePictureDTO toDto(ProfilePictureEntity e) {
        return new ProfilePictureDTO(e.getId(), ImageConversionHelper.blobToByteArray(e.getPicture()));
    }
}
