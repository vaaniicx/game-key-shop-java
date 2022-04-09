package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.repository.ShoppingCartGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ShoppingCartGameService {

    @Autowired
    private ShoppingCartGameRepository repository;

    public List<ShoppingCartGameEntity> getShoppingCartGameByShoppingCartId(long id) {
        return repository.findByShoppingCartId(id);
    }

    public void save(ShoppingCartGameEntity e) {
        repository.save(e);
    }

    public void deleteAllById(Set<ShoppingCartGameEntity> e) {
        e.forEach(scg -> repository.deleteById(scg.getId()));
    }
}
