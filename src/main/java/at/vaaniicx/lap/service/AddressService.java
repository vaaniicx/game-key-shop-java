package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.KeyCodeNotFoundException;
import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }

    public AddressEntity getAddressById(Long id) {
        Optional<AddressEntity> entity = addressRepository.findById(id);

        if (!entity.isPresent()) {
            throw new KeyCodeNotFoundException();
        }

        return entity.get();
    }
}
