package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }
}
