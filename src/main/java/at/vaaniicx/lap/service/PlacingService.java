package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.placing.PlacingNotFoundException;
import at.vaaniicx.lap.model.entity.PlacingEntity;
import at.vaaniicx.lap.repository.PlacingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacingService {

    private final PlacingRepository placingRepository;

    public PlacingService(PlacingRepository placingRepository) {
        this.placingRepository = placingRepository;
    }

    public List<PlacingEntity> getAllPlacings() {
        return placingRepository.findAll();
    }

    public List<PlacingEntity> getAllPlacingsByPersonId(Long personId) {
        return placingRepository.findAllByPersonId(personId);
    }

    public PlacingEntity save(PlacingEntity e) {
        return placingRepository.save(e);
    }

    public PlacingEntity getPlacingByPlacingId(Long id) {
        Optional<PlacingEntity> entity = placingRepository.findById(id);

        if (!entity.isPresent()) {
            throw new PlacingNotFoundException();
        }

        return entity.get();
    }
}
