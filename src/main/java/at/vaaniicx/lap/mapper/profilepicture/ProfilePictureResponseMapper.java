package at.vaaniicx.lap.mapper.profilepicture;

import at.vaaniicx.lap.model.entity.ProfilePictureEntity;
import at.vaaniicx.lap.model.response.profilepicture.ProfilePictureResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ProfilePictureResponseMapper {

    ProfilePictureResponse entityToResponse(ProfilePictureEntity source);
}
