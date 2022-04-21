package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.key.KeyCodeNotFoundException;
import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    /**
     * Retourniert alle Veröffentlicher.
     *
     * @return - Liste aller Veröffentlicher
     */
    public List<PublisherEntity> getAllPublisher() {
        return publisherRepository.findAll();
    }

    /**
     * Retourniert den zur übergebenen ID zugehörigen Veräffentlicher.
     *
     * @return - Veräffentlicher zur übergebenen ID
     */
    public PublisherEntity getPublisherById(Long id) {
        Optional<PublisherEntity> entity = publisherRepository.findById(id);

        if (!entity.isPresent()) {
            throw new KeyCodeNotFoundException();
        }

        return entity.get();
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public PublisherEntity save(PublisherEntity entity) {
        return publisherRepository.save(entity);
    }

    /**
     * Löscht das Entity-Objekt zur übergebenen ID.
     *
     * @param id - ID des zu löschenden Objekt
     */
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
}
