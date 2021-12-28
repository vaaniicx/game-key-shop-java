package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.ShoppingCartGameDTO;
import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartGameMapper {

    public static ShoppingCartGameDTO toDto(ShoppingCartGameEntity e) {
        return new ShoppingCartGameDTO(e.getShoppingCart().getId(), GameMapper.toDto(e.getGame()), e.getAmount());
    }
}
