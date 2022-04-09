package at.vaaniicx.lap.mapper.shoppingcart;

import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.response.shoppingcart.ShoppingCartResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ShoppingCartResponseMapper {

    ShoppingCartResponse entityToResponse(ShoppingCartEntity source);
}
