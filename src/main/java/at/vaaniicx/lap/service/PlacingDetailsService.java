package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.repository.PlacingDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class PlacingDetailsService {

    private final PlacingDetailsRepository placingDetailsRepository;

    public PlacingDetailsService(PlacingDetailsRepository placingDetailsRepository) {
        this.placingDetailsRepository = placingDetailsRepository;
    }

    public PlacingDetailsEntity save(PlacingDetailsEntity e) {
        return placingDetailsRepository.save(e);
    }
}
