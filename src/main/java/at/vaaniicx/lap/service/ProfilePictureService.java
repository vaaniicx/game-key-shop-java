package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.ProfilePictureEntity;
import at.vaaniicx.lap.repository.ProfilePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilePictureService {

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    public ProfilePictureEntity save(ProfilePictureEntity e) {
        return profilePictureRepository.save(e);
    }
}
