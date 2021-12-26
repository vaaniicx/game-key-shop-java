package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.DeveloperNotFoundException;
import at.vaaniicx.lap.model.entity.DeveloperEntity;
import at.vaaniicx.lap.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public List<DeveloperEntity> getAllDeveloper() {
        List<DeveloperEntity> entities = developerRepository.findAll();

        if (entities.isEmpty()) {
            return new ArrayList<>();
        }

        return entities;
    }

    public DeveloperEntity getDeveloperById(Long id) {
        Optional<DeveloperEntity> entity = developerRepository.findById(id);

        if (!entity.isPresent()) {
            throw new DeveloperNotFoundException();
        }

        return entity.get();
    }

    public DeveloperEntity getDeveloperByDeveloper(String name) {
        Optional<DeveloperEntity> entity = developerRepository.findByDeveloper(name);

        if (!entity.isPresent()) {
            throw new DeveloperNotFoundException();
        }

        return entity.get();
    }
}
