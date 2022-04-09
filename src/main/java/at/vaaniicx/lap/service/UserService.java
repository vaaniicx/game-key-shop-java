package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.user.UserNotFoundException;
import at.vaaniicx.lap.model.entity.*;
import at.vaaniicx.lap.model.request.RegisterRequest;
import at.vaaniicx.lap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public boolean isUserActive(String email) {
        return getUserByEmail(email).isActive();
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

    public UserEntity registerUser(RegisterRequest req) {
        LocationEntity location = new LocationEntity(req.getPostal(), req.getLocation());
        AddressEntity address = new AddressEntity(req.getStreet(), req.getHouseNumber(), req.getDoor(), req.getStair());
        PersonEntity person = new PersonEntity(req.getFirstName(), req.getLastName(), req.getBirthDate());
        UserEntity user = new UserEntity(req.getEmail(), passwordEncoder.encode(req.getPassword()), true, Instant.now(), roleService.getRoleByRoleName("Kunde"));

        user.setPerson(person);
        person.setAddress(address);
        address.setLocation(location);
        location.setCountry(countryService.getCountryById(req.getCountryId()));

        UserEntity persistedUser = userRepository.save(user);

        ShoppingCartEntity shoppingCart =
                new ShoppingCartEntity(personService.getPersonById(persistedUser.getPerson().getId()), 0);
        shoppingCartService.saveShoppingCart(shoppingCart);

        return persistedUser;
    }

    public UserEntity getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return getUserByEmail(authentication.getName());
    }

    public UserEntity getUserByPersonId(Long id) {
        Optional<UserEntity> entity = userRepository.findByPersonId(id);

        if (!entity.isPresent()) {
            throw new UserNotFoundException();
        }

        return entity.get();
    }

    public void updateLastLogin(String username) {
        Optional<UserEntity> entity = userRepository.findByEmail(username);

        if (!entity.isPresent()) {
            throw new UserNotFoundException();
        }

        UserEntity user = entity.get();
        user.setLastLogin(Instant.now());

        saveUser(user);
    }

    public List<UserEntity> findUserByRoleId(Long id) {
        return userRepository.findByRoleId(id);
    }
}
