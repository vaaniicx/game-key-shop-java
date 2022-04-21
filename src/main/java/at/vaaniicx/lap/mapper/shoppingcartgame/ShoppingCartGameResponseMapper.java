package at.vaaniicx.lap.mapper.shoppingcartgame;

import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.response.shoppingcartgame.ShoppingCartGameResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartGameResponseMapper {

    ShoppingCartGameResponse entityToResponse(ShoppingCartGameEntity source);
}
