package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Retourniert alle Adressen.
     *
     * @return - Liste mit allen Adressen
     */
    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }
}
