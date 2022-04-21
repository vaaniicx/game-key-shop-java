package at.vaaniicx.lap.mapper.profilepicture;

import at.vaaniicx.lap.model.entity.ProfilePictureEntity;
import at.vaaniicx.lap.model.response.profilepicture.ProfilePictureResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfilePictureResponseMapper {

    ProfilePictureResponseMapper INSTANCE = Mappers.getMapper(ProfilePictureResponseMapper.class);

    ProfilePictureResponse entityToResponse(ProfilePictureEntity source);
}
