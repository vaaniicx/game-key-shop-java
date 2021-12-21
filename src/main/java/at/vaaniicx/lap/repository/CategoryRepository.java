package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    @Override
    List<CategoryEntity> findAll();

    @Override
    Optional<CategoryEntity> findById(Long id);

    Optional<CategoryEntity> findByCategory(String category);
}
