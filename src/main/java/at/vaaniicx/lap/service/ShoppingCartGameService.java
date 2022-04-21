package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.repository.ShoppingCartGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ShoppingCartGameService {

    private final ShoppingCartGameRepository shoppingCartGameRepository;

    @Autowired
    public ShoppingCartGameService(ShoppingCartGameRepository shoppingCartGameRepository) {
        this.shoppingCartGameRepository = shoppingCartGameRepository;
    }

    public List<ShoppingCartGameEntity> getShoppingCartGameByShoppingCartId(long id) {
        return shoppingCartGameRepository.findByShoppingCartId(id);
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public ShoppingCartGameEntity save(ShoppingCartGameEntity entity) {
        return shoppingCartGameRepository.save(entity);
    }

    /**
     * Löscht die übergebenen Entity-Objekte.
     *
     * @param entities - Liste der zu löschenden Entities
     */
    public void deleteAllById(Set<ShoppingCartGameEntity> entities) {
        entities.forEach(e -> shoppingCartGameRepository.deleteById(e.getId()));
    }
}
