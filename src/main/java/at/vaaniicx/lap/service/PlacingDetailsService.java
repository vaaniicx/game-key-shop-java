package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.repository.PlacingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacingDetailsService {

    private final PlacingDetailsRepository placingDetailsRepository;

    @Autowired
    public PlacingDetailsService(PlacingDetailsRepository placingDetailsRepository) {
        this.placingDetailsRepository = placingDetailsRepository;
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public PlacingDetailsEntity save(PlacingDetailsEntity entity) {
        return placingDetailsRepository.save(entity);
    }
}
