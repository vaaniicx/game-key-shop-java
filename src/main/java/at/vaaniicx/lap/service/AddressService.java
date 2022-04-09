package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }
}
