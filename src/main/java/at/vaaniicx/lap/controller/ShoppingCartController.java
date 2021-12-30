package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.ShoppingCartDTO;
import at.vaaniicx.lap.model.mapper.ShoppingCartMapper;
import at.vaaniicx.lap.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService service;

    @GetMapping
    public List<ShoppingCartDTO> getAll() {
        return service.getAllShoppingCarts().stream().map(ShoppingCartMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ShoppingCartDTO getById(@PathVariable("id") Long shoppingCartId) {
        return ShoppingCartMapper.toDto(service.getShoppingCartById(shoppingCartId));
    }

    @GetMapping("/person/{id}")
    public ShoppingCartDTO getByPersonId(@PathVariable("id") Long personId) {
        return ShoppingCartMapper.toDto(service.getShoppingCartByPersonId(personId));
    }
}
