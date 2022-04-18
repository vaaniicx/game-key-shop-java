package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.ProfilePictureEntity;
import at.vaaniicx.lap.repository.ProfilePictureRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfilePictureService {

    private final ProfilePictureRepository profilePictureRepository;

    public ProfilePictureService(ProfilePictureRepository profilePictureRepository) {
        this.profilePictureRepository = profilePictureRepository;
    }

    public ProfilePictureEntity save(ProfilePictureEntity e) {
        return profilePictureRepository.save(e);
    }
}
