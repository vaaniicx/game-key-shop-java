package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.repository.PlacingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacingDetailsService {

    @Autowired
    private PlacingDetailsRepository placingDetailsRepository;

    public PlacingDetailsEntity save(PlacingDetailsEntity e) {
        return placingDetailsRepository.save(e);
    }
}
