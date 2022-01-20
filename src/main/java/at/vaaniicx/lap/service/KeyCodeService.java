package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.KeyCodeNotFoundException;
import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.repository.KeyCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeyCodeService {

    @Autowired
    private KeyCodeRepository keyCodeRepository;

    public List<KeyCodeEntity> getAllKeyCodes() {
        return keyCodeRepository.findAll();
    }

    public KeyCodeEntity getKeyCodeById(Long keyId) {
        Optional<KeyCodeEntity> entity = keyCodeRepository.findById(keyId);

        if (!entity.isPresent()) {
            throw new KeyCodeNotFoundException();
        }

        return entity.get();
    }

    public List<KeyCodeEntity> getAllKeyCodesByGameId(Long id) {
        return keyCodeRepository.findByGameId(id);
    }

    public List<KeyCodeEntity> getAllAvailableKeyCodesByGameId(Long gameId) {
        return keyCodeRepository.findByGameIdAndSold(gameId, false);
    }

    public KeyCodeEntity saveKeyCode(KeyCodeEntity entity) {
        return keyCodeRepository.save(entity);
    }

    public boolean deleteKeyCodeById(Long keyId) {
        try {
            keyCodeRepository.deleteById(keyId);
        } catch (EmptyResultDataAccessException e) {
            throw new KeyCodeNotFoundException();
        }
        return !keyCodeRepository.existsById(keyId);
    }

    public Long getKeyCountByGameIdAndSold(Long gameId, boolean sold) {
        return keyCodeRepository.countByGameIdAndSold(gameId, sold);
    }

    public Long getKeyCountByGame(Long gameId) {
        return keyCodeRepository.countByGameId(gameId);
    }
}
