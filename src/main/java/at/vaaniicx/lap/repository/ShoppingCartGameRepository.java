package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.entity.pk.ShoppingCartGamePk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartGameRepository extends CrudRepository<ShoppingCartGameEntity, ShoppingCartGamePk> {

    @Override
    List<ShoppingCartGameEntity> findAll();

    @Override
    Optional<ShoppingCartGameEntity> findById(ShoppingCartGamePk privateKey);

    List<ShoppingCartGameEntity> findByShoppingCartId(Long id);
}
