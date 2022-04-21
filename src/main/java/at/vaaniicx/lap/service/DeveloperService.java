package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.developer.DeveloperNotFoundException;
import at.vaaniicx.lap.model.entity.DeveloperEntity;
import at.vaaniicx.lap.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    @Autowired
    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    /**
     * Retourniert alle Entwickler.
     *
     * @return - Liste aller Entwicklern
     */
    public List<DeveloperEntity> getAllDeveloper() {
        return developerRepository.findAll();
    }

    /**
     * Retourniert den zur übergebenen ID zugehörigen Entwickler.
     *
     * @return - Entwickler zur übergebenen ID
     */
    public DeveloperEntity getDeveloperById(Long id) {
        Optional<DeveloperEntity> entity = developerRepository.findById(id);

        if (!entity.isPresent()) {
            throw new DeveloperNotFoundException();
        }

        return entity.get();
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public DeveloperEntity save(DeveloperEntity entity) {
        return developerRepository.save(entity);
    }

    /**
     * Löscht das Entity-Objekt zur übergebenen ID.
     *
     * @param id - ID des zu löschenden Objekt
     */
    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }
}
