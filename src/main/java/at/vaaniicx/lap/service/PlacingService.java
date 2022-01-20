package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.PlacingEntity;
import at.vaaniicx.lap.repository.PlacingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacingService {

    @Autowired
    private PlacingRepository placingRepository;

    public List<PlacingEntity> getAllPlacings() {
        return placingRepository.findAll();
    }

    public List<PlacingEntity> getAllPlacingsByPersonId(Long personId) {
        return placingRepository.findAllByPersonId(personId);
    }

    public PlacingEntity save(PlacingEntity e) {
        return placingRepository.save(e);
    }
}
