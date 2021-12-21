package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.GamePictureNotFoundException;
import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.repository.GamePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamePictureService {

    @Autowired
    private GamePictureRepository gamePictureRepository;

    public List<GamePictureEntity> getAllGamePictures() {
        return gamePictureRepository.findAll();
    }

    public GamePictureEntity getGamePictureById(Long id) {
        Optional<GamePictureEntity> entity = gamePictureRepository.findById(id);

        if (!entity.isPresent()) {
            throw new GamePictureNotFoundException();
        }

        return entity.get();
    }

    public List<GamePictureEntity> getGamePicturesForGameId(Long gameId) {
        return gamePictureRepository.findByGameId(gameId);
    }

    public GamePictureEntity getThumbPictureForGameId(Long gameId) {
        Optional<GamePictureEntity> entity =
                gamePictureRepository.findByGameId(gameId).stream().filter(GamePictureEntity::isThumb).findFirst();

        if (!entity.isPresent()) {
//            throw new GamePictureNotFoundException();
            return null;
        }

        return entity.get();
    }
}
