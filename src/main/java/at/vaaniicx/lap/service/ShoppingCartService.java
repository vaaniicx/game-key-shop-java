package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.ShoppingCartNotFoundException;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    public List<ShoppingCartEntity> getAllShoppingCarts() {
        return repository.findAll();
    }

    public ShoppingCartEntity getShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> entity = repository.findById(id);

        if (!entity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        return entity.get();
    }

    public ShoppingCartEntity getShoppingCartByPersonId(Long id) {
        Optional<ShoppingCartEntity> entity = repository.findByPersonId(id);

        if (!entity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        return entity.get();
    }

    public ShoppingCartEntity saveShoppingCart(ShoppingCartEntity entity) {
        return repository.save(entity);
    }

    public ShoppingCartEntity createShoppingCart(Long personId) {
        return repository.save(new ShoppingCartEntity());
    }
}
