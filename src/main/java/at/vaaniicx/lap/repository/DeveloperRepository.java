package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.DeveloperEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends CrudRepository<DeveloperEntity, Long> {

    @Override
    List<DeveloperEntity> findAll();

    @Override
    Optional<DeveloperEntity> findById(Long id);
}
