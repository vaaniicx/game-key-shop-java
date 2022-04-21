package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.placing.PlacingNotFoundException;
import at.vaaniicx.lap.model.entity.PlacingEntity;
import at.vaaniicx.lap.repository.PlacingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacingService {

    private final PlacingRepository placingRepository;

    @Autowired
    public PlacingService(PlacingRepository placingRepository) {
        this.placingRepository = placingRepository;
    }

    /**
     * Retourniert alle Bestellungen.
     *
     * @return - Liste mit allen Bestellungen
     */
    public List<PlacingEntity> getAllPlacings() {
        return placingRepository.findAll();
    }

    /**
     * Retourniert alle zur übergebenen Person-ID zugehörigen Bestellungen.
     *
     * @return - Liste mit allen Bestellungen einer Person
     */
    public List<PlacingEntity> getAllPlacingsByPersonId(Long personId) {
        return placingRepository.findAllByPersonId(personId);
    }

    /**
     * Retourniert die zur übergebenen ID zugehörige Bestellung.
     *
     * @return - Bestellung zur übergebenen ID
     */
    public PlacingEntity getPlacingByPlacingId(Long id) {
        Optional<PlacingEntity> entity = placingRepository.findById(id);

        if (!entity.isPresent()) {
            throw new PlacingNotFoundException();
        }

        return entity.get();
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public PlacingEntity save(PlacingEntity entity) {
        return placingRepository.save(entity);
    }
}
