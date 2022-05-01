package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.key.KeyCodeNotFoundException;
import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.repository.KeyCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class KeyCodeService {

    private final KeyCodeRepository keyCodeRepository;

    @Autowired
    public KeyCodeService(KeyCodeRepository keyCodeRepository) {
        this.keyCodeRepository = keyCodeRepository;
    }

    /**
     * Retourniert alle zur übergebenen Spiele-ID zugehörigen Schlüssel.
     *
     * @return - Liste mit allen Schlüssel eines Spiels
     */
    public List<KeyCodeEntity> getAllKeyCodesByGameId(Long id) {
        return keyCodeRepository.findByGameId(id);
    }

    /**
     * Retourniert den zur übergebenen ID zugehörigen Schlüssel.
     *
     * @return - Schlüssel zur ID
     */
    public KeyCodeEntity getKeyCodeById(Long id) {

        Optional<KeyCodeEntity> entity = keyCodeRepository.findById(id);

        if (!entity.isPresent()) {
            throw new KeyCodeNotFoundException();
        }

        return entity.get();
    }

    /**
     * Retourniert alle zur übergebenen Spiele-ID zugehörigen, verfügbaren Schlüssel.
     *
     * @return - Liste mit allen verfügbaren Schlüssel eines Spiels
     */
    public List<KeyCodeEntity> getAllAvailableKeyCodesByGameId(Long gameId) {
        return keyCodeRepository.findByGameIdAndSold(gameId, false);
    }

    /**
     * Retourniert alle zur übergebenen Spiele-ID zugehörigen Schlüssel.
     *
     * @return - Liste mit allen Schlüssel eines Spiels
     */
    public Long getKeyCountByGameIdAndSold(Long gameId, boolean sold) {
        return keyCodeRepository.countByGameIdAndSold(gameId, sold);
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public KeyCodeEntity save(KeyCodeEntity entity) {
        return keyCodeRepository.save(entity);
    }

    /**
     * Löscht das Entity-Objekt zur übergebenen ID.
     *
     * @param id - ID des zu löschenden Objekt
     */
    public void deleteById(Long id) {
        try {
            keyCodeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new KeyCodeNotFoundException();
        }
    }

    /**
     * Löscht die übergebenen Entity-Objekte.
     *
     * @param entities - Liste der zu löschenden Entities
     */
    public void deleteAll(Set<KeyCodeEntity> entities) {
        keyCodeRepository.deleteAll(entities);
    }
}
