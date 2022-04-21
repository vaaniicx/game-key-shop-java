package at.vaaniicx.lap.mapper.shoppingcart;

import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.response.shoppingcart.ShoppingCartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShoppingCartResponseMapper {

    ShoppingCartResponseMapper INSTANCE = Mappers.getMapper(ShoppingCartResponseMapper.class);

    ShoppingCartResponse entityToResponse(ShoppingCartEntity source);
}
