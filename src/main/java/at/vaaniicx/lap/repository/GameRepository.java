package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.GameEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<GameEntity, Long> {

    @Override
    List<GameEntity> findAll();

    @Override
    Optional<GameEntity> findById(Long id);

    List<GameEntity> findByOrderByTitleAsc();

    List<GameEntity> findByPublisherId(Long id);

    List<GameEntity> findByDeveloperId(Long id);
}
