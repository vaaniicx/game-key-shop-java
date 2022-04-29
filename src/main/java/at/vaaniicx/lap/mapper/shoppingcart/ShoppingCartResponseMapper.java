package at.vaaniicx.lap.mapper.shoppingcart;

import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.response.shoppingcart.ShoppingCartResponse;
import at.vaaniicx.lap.model.response.shoppingcart.SlimShoppingCartResponse;
import at.vaaniicx.lap.model.response.shoppingcartgame.ShoppingCartGameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShoppingCartResponseMapper {

    ShoppingCartResponseMapper INSTANCE = Mappers.getMapper(ShoppingCartResponseMapper.class);

    ShoppingCartResponse entityToResponse(ShoppingCartEntity source);

    SlimShoppingCartResponse entityToSlimResponse(ShoppingCartEntity source);
}
