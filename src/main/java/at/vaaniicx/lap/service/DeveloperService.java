package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.developer.DeveloperNotFoundException;
import at.vaaniicx.lap.model.entity.DeveloperEntity;
import at.vaaniicx.lap.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

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

    public DeveloperEntity registerDeveloper(String name) {
        return developerRepository.save(DeveloperEntity.builder().developer(name).build());
    }

    public DeveloperEntity updateDeveloper(DeveloperEntity e) {
        return developerRepository.save(e);
    }

    public void deleteDeveloperById(Long id) {
        developerRepository.deleteById(id);
    }
}
