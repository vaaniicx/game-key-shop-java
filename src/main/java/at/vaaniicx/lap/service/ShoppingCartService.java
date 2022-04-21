package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.shoppingcart.ShoppingCartNotFoundException;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /**
     * Retourniert alle Warenkörbe.
     *
     * @return - Liste aller Warenkörbe
     */
    public List<ShoppingCartEntity> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    /**
     * Retourniert den zur übergebenen ID zugehörigen Warenkorb.
     *
     * @return - Warenkorb zur übergebenen ID
     */
    public ShoppingCartEntity getShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> entity = shoppingCartRepository.findById(id);

        if (!entity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        return entity.get();
    }

    /**
     * Retourniert den zur übergebenen Person-ID zugehörigen Warenkorb.
     *
     * @return - Warenkorb zur übergebenen Person-ID
     */
    public ShoppingCartEntity getShoppingCartByPersonId(Long id) {
        Optional<ShoppingCartEntity> entity = shoppingCartRepository.findByPersonId(id);

        if (!entity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        return entity.get();
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public ShoppingCartEntity save(ShoppingCartEntity entity) {
        return shoppingCartRepository.save(entity);
    }
}
