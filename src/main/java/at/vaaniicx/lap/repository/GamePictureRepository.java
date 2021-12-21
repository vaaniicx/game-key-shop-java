package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.GamePictureEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GamePictureRepository extends CrudRepository<GamePictureEntity, Long> {

    @Override
    List<GamePictureEntity> findAll();

    @Override
    Optional<GamePictureEntity> findById(Long id);

    List<GamePictureEntity> findByGameId(Long id);
}
