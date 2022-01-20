package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.PlacingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlacingRepository extends CrudRepository<PlacingEntity, Long> {

    @Override
    List<PlacingEntity> findAll();

    @Override
    Optional<PlacingEntity> findById(Long id);

    List<PlacingEntity> findAllByPersonId(Long id);
}
