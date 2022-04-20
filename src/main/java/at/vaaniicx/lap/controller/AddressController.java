package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.address.AddressResponseMapper;
import at.vaaniicx.lap.model.response.address.AddressResponse;
import at.vaaniicx.lap.service.AddressService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    private final AddressResponseMapper addressMapper;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
        this.addressMapper = Mappers.getMapper(AddressResponseMapper.class);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {

        List<AddressResponse> addressResponses = addressService.getAllAddresses()
                .stream()
                .map(addressMapper::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(addressResponses);
    }
}
