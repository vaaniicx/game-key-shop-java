package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.UserNotFoundException;
import at.vaaniicx.lap.model.entity.AddressEntity;
import at.vaaniicx.lap.model.entity.LocationEntity;
import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.mapper.AddressMapper;
import at.vaaniicx.lap.model.mapper.LocationMapper;
import at.vaaniicx.lap.model.mapper.PersonMapper;
import at.vaaniicx.lap.model.mapper.UserMapper;
import at.vaaniicx.lap.model.request.RegisterRequest;
import at.vaaniicx.lap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryService countryService;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);

        if (!entity.isPresent()) {
            throw new UserNotFoundException();
        }
        return entity.get();
    }

    public UserEntity getUserByEmail(String email) {
        Optional<UserEntity> entity = userRepository.findByEmail(email);

        if (!entity.isPresent()) {
            throw new UserNotFoundException();
        }
        return entity.get();
    }

    public UserEntity saveUser(UserEntity entity) {
        return userRepository.save(entity);
    }

    public boolean isEmailAlreadyRegistered(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.isPresent();
    }

    public UserEntity registerUser(RegisterRequest request) {
        LocationEntity locationEntity = LocationMapper.toEntity(request);
        AddressEntity addressEntity = AddressMapper.toEntity(request);
        PersonEntity personEntity = PersonMapper.toEntity(request);
        UserEntity userEntity = UserMapper.toEntity(request);

        userEntity.setPerson(personEntity);
        personEntity.setAddress(addressEntity);
        addressEntity.setLocation(locationEntity);
        locationEntity.setCountry(countryService.getCountryById(request.getCountryId()));

        return userRepository.save(userEntity);
    }

    public UserEntity getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return getUserByEmail(authentication.getName());
    }

}
