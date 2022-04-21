package at.vaaniicx.lap.mapper.shoppingcartgame;

import at.vaaniicx.lap.mapper.game.GameResponseMapper;
import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.response.shoppingcartgame.ShoppingCartGameResponse;

public class ShoppingCartGameResponseMapperImpl implements ShoppingCartGameResponseMapper {

    @Override
    public ShoppingCartGameResponse entityToResponse(ShoppingCartGameEntity source) {
        if (source == null) {
            return null;
        }

        ShoppingCartGameResponse destination = new ShoppingCartGameResponse();
        destination.setShoppingCartId(source.getShoppingCart().getId());
        destination.setGame(GameResponseMapper.INSTANCE.entityToResponse(source.getGame()));
        destination.setAmount(source.getAmount());

        return destination;
    }
}
