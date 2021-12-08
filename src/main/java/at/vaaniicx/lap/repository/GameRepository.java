package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.GameEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<GameEntity, Long> {

    @Override
    List<GameEntity> findAll();

    @Override
    Optional<GameEntity> findById(Long id);
}
