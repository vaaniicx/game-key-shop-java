package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.ShoppingCartGameNotFoundException;
import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.entity.pk.ShoppingCartGamePk;
import at.vaaniicx.lap.repository.ShoppingCartGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartGameService {

    @Autowired
    private ShoppingCartGameRepository repository;

    public List<ShoppingCartGameEntity> getAllShoppingCartGames() {
        return repository.findAll();
    }

    public ShoppingCartGameEntity getShoppingCartGameById(ShoppingCartGamePk pk) {
        Optional<ShoppingCartGameEntity> entity = repository.findById(pk);

        if (!entity.isPresent()) {
            throw new ShoppingCartGameNotFoundException();
        }

        return entity.get();
    }

    public List<ShoppingCartGameEntity> getShoppingCartGameByShoppingCartId(long id) {
        return repository.findByShoppingCartId(id);
    }

    public void save(ShoppingCartGameEntity e) {
        repository.save(e);
    }

    public void deleteAllById(List<ShoppingCartGameEntity> e) {
        e.forEach(scg -> repository.deleteById(scg.getId()));
    }
}
