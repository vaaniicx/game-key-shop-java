package at.vaaniicx.lap.mapper.shoppingcartgame;

import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.response.shoppingcartgame.ShoppingCartGameResponse;
import at.vaaniicx.lap.model.response.shoppingcartgame.SlimShoppingCartGameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ShoppingCartGameResponseMapper {

    ShoppingCartGameResponseMapper INSTANCE = Mappers.getMapper(ShoppingCartGameResponseMapper.class);

    ShoppingCartGameResponse entityToResponse(ShoppingCartGameEntity source);

    SlimShoppingCartGameResponse entityToSlimResponse(ShoppingCartGameEntity source);
}
