package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.ShoppingCartDTO;
import at.vaaniicx.lap.model.entity.ShoppingCartEntity;
import at.vaaniicx.lap.service.ShoppingCartGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class ShoppingCartMapper {

    private static ShoppingCartGameService shoppingCartGameService;

    @Autowired
    private ShoppingCartGameService shoppingCartGameService0;

    @PostConstruct
    private void initStaticFields() {
        shoppingCartGameService = this.shoppingCartGameService0;
    }

    public static ShoppingCartDTO toDto(ShoppingCartEntity e) {
        return new ShoppingCartDTO(e.getId(), e.getPerson().getId(), e.getTotalPrice(),
                shoppingCartGameService.getShoppingCartGameByShoppingCartId(e.getId()).stream().map(ShoppingCartGameMapper::toDto).collect(Collectors.toList()));
    }
}
