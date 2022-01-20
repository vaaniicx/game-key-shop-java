package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.repository.PlacingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacingDetailsService {

    @Autowired
    private PlacingDetailsRepository placingDetailsRepository;

    public List<PlacingDetailsEntity> getAllPlacingDetails() {
        return placingDetailsRepository.findAll();
    }

    public List<PlacingDetailsEntity> getAllPlacingDetailsByPlacingId(Long placingId) {
        return placingDetailsRepository.findAllByPlacingId(placingId);
    }

    public PlacingDetailsEntity save(PlacingDetailsEntity e) {
        return placingDetailsRepository.save(e);
    }
}
