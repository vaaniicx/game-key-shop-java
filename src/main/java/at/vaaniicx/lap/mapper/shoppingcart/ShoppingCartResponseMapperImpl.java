package at.vaaniicx.lap.mapper.shoppingcart;

import at.vaaniicx.lap.mapper.shoppingcartgame.ShoppingCartGameResponseMapper;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.response.shoppingcart.ShoppingCartResponse;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

public class ShoppingCartResponseMapperImpl implements ShoppingCartResponseMapper {

    private ShoppingCartGameResponseMapper shoppingCartGameMapper = Mappers.getMapper(ShoppingCartGameResponseMapper.class);

    @Override
    public ShoppingCartResponse entityToResponse(ShoppingCartEntity source) {
        if (source == null) {
            return null;
        }

        ShoppingCartResponse destination = new ShoppingCartResponse();
        destination.setShoppingCartId(source.getId());
        destination.setPersonId(source.getPerson().getId());
        destination.setTotalPrice(source.getTotalPrice());
        destination.setShoppingCartGames(source.getGames().stream().map(e -> shoppingCartGameMapper.entityToResponse(e)).collect(Collectors.toList()));

        return destination;
    }
}
