package at.vaaniicx.lap.mapper.shoppingcartgame;

import at.vaaniicx.lap.mapper.game.GameResponseMapper;
import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import at.vaaniicx.lap.model.response.shoppingcartgame.ShoppingCartGameResponse;
import org.mapstruct.factory.Mappers;

public class ShoppingCartGameResponseMapperImpl implements ShoppingCartGameResponseMapper {

    private final GameResponseMapper gameMapper = Mappers.getMapper(GameResponseMapper.class);

    @Override
    public ShoppingCartGameResponse entityToResponse(ShoppingCartGameEntity source) {
        if (source == null) {
            return null;
        }

        ShoppingCartGameResponse destination = new ShoppingCartGameResponse();
        destination.setShoppingCartId(source.getShoppingCart().getId());
        destination.setGame(gameMapper.entityToResponse(source.getGame()));
        destination.setAmount(source.getAmount());

        return destination;
    }
}
