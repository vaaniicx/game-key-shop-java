package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface KeyCodeRepository extends CrudRepository<KeyCodeEntity, Long> {

    @Override
    List<KeyCodeEntity> findAll();

    @Override
    Optional<KeyCodeEntity> findById(Long id);

    Long countByGameIdAndSold(Long id, boolean sold);

    List<KeyCodeEntity> findByGameId(Long id);

    List<KeyCodeEntity> findByGameIdAndSold(Long id, boolean sold);
}
