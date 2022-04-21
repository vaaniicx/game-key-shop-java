package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.gamepicture.GamePictureNotFoundException;
import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.repository.GamePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GamePictureService {

    private final GamePictureRepository gamePictureRepository;

    @Autowired
    public GamePictureService(GamePictureRepository gamePictureRepository) {
        this.gamePictureRepository = gamePictureRepository;
    }

    /**
     * Retourniert das zur übergebenen ID zugehörige Spielebild.
     *
     * @return - Spielebild zur übergebenen ID
     */
    public GamePictureEntity getGamePictureById(Long id) {
        Optional<GamePictureEntity> entity = gamePictureRepository.findById(id);

        if (!entity.isPresent()) {
            throw new GamePictureNotFoundException();
        }

        return entity.get();
    }

    /**
     * Retourniert das zur übergebenen Game-ID zugehörige Thumbnail.
     *
     * @return - Thumbnail zur Game-ID
     */
    public GamePictureEntity getThumbnailByGameId(Long gameId) {
        Optional<GamePictureEntity> entity =
                gamePictureRepository.findByGameId(gameId).stream().filter(GamePictureEntity::isThumb).findFirst();

        return entity.orElse(null);
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public GamePictureEntity save(GamePictureEntity entity) {
        return gamePictureRepository.save(entity);
    }
}
