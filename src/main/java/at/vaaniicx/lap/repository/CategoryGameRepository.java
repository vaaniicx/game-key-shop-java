package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryGameRepository extends CrudRepository<CategoryGameEntity, CategoryGamePk> {

    @Override
    List<CategoryGameEntity> findAll();

    @Override
    Optional<CategoryGameEntity> findById(CategoryGamePk privateKey);

    List<CategoryGameEntity> findByGameId(Long id);

    List<CategoryGameEntity> findByCategoryId(Long id);
}
