package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.ShoppingCartNotFoundException;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCartEntity> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCartEntity getShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> entity = shoppingCartRepository.findById(id);

        if (!entity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        return entity.get();
    }

    public ShoppingCartEntity getShoppingCartByPersonId(Long id) {
        Optional<ShoppingCartEntity> entity = shoppingCartRepository.findByPersonId(id);

        if (!entity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        return entity.get();
    }

    public ShoppingCartEntity saveShoppingCart(ShoppingCartEntity entity) {
        return shoppingCartRepository.save(entity);
    }
}
