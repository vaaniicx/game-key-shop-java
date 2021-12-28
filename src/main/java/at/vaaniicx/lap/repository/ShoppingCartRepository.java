package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCartEntity, Long> {

    @Override
    List<ShoppingCartEntity> findAll();

    @Override
    Optional<ShoppingCartEntity> findById(Long id);

    Optional<ShoppingCartEntity> findByPersonId(Long id);
}
