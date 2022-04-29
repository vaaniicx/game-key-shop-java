package at.vaaniicx.lap.mapper.shoppingcart;

import at.vaaniicx.lap.mapper.shoppingcartgame.ShoppingCartGameResponseMapper;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.model.response.shoppingcart.ShoppingCartResponse;
import at.vaaniicx.lap.model.response.shoppingcart.SlimShoppingCartResponse;

import java.util.stream.Collectors;

public class ShoppingCartResponseMapperImpl implements ShoppingCartResponseMapper {

    @Override
    public ShoppingCartResponse entityToResponse(ShoppingCartEntity source) {
        if (source == null) {
            return null;
        }

        ShoppingCartResponse destination = new ShoppingCartResponse();
        destination.setShoppingCartId(source.getId());
        destination.setPersonId(source.getPerson().getId());
        destination.setTotalPrice(source.getTotalPrice());
        destination.setShoppingCartGames(source.getGames()
                .stream()
                .map(ShoppingCartGameResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList())
        );

        return destination;
    }

    @Override
    public SlimShoppingCartResponse entityToSlimResponse(ShoppingCartEntity source) {
        if (source == null) {
            return null;
        }

        SlimShoppingCartResponse destination = new SlimShoppingCartResponse();
        destination.setShoppingCartId(source.getId());
        destination.setPersonId(source.getPerson().getId());
        destination.setTotalPrice(source.getTotalPrice());
        destination.setShoppingCartGames(source.getGames()
                .stream()
                .map(ShoppingCartGameResponseMapper.INSTANCE::entityToSlimResponse)
                .collect(Collectors.toList())
        );

        return destination;
    }
}
