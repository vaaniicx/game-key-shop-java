package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.repository.ShoppingCartGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ShoppingCartGameService {

    private final ShoppingCartGameRepository shoppingCartGameRepository;

    public ShoppingCartGameService(ShoppingCartGameRepository shoppingCartGameRepository) {
        this.shoppingCartGameRepository = shoppingCartGameRepository;
    }

    public List<ShoppingCartGameEntity> getShoppingCartGameByShoppingCartId(long id) {
        return shoppingCartGameRepository.findByShoppingCartId(id);
    }

    public void save(ShoppingCartGameEntity e) {
        shoppingCartGameRepository.save(e);
    }

    public void deleteAllById(Set<ShoppingCartGameEntity> e) {
        e.forEach(scg -> shoppingCartGameRepository.deleteById(scg.getId()));
    }
}
