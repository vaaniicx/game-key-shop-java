package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.ShoppingCartDTO;
import at.vaaniicx.lap.model.mapper.ShoppingCartMapper;
import at.vaaniicx.lap.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ShoppingCartDTO getById(@PathVariable Long id) {
        return ShoppingCartMapper.toDto(service.getShoppingCartById(id));
    }

    @GetMapping("/person/{id}")
    public ShoppingCartDTO getByPersonId(@PathVariable Long id) {
        return ShoppingCartMapper.toDto(service.getShoppingCartByPersonId(id));
    }
}
