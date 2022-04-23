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

    private final UserRepository userRepository;
    private final CountryService countryService;
    private final RoleService roleService;
    private final PersonService personService;
    private final ShoppingCartService shoppingCartService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CountryService countryService, RoleService roleService,
                       PersonService personService, ShoppingCartService shoppingCartService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.countryService = countryService;
        this.roleService = roleService;
        this.personService = personService;
        this.shoppingCartService = shoppingCartService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retourniert alle Benutzer.
     *
     * @return - Liste aller Benutzer
     */
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retourniert die zur übergebenen Rollen-ID zugehörigen Benutzer.
     *
     * @return - Liste aller Benutzer zur Rollen-ID
     */
    public List<UserEntity> getUserByRoleId(Long id) {
        return userRepository.findByRoleId(id);
    }

    /**
     * Retourniert den zur übergebenen ID zugehörigen Benutzer.
     *
     * @return - Benutzer zur ID
     */
    public UserEntity getUserById(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);

        if (!entity.isPresent()) {
            throw new UserNotFoundException();
        }
        return entity.get();
    }

    /**
     * Retourniert ob ein Benutzer ein aktives Benutzerkonto bestitzt oder nicht.
     *
     * @return - Aktives Benutzerkonto (T/F)
     */
    public boolean isAccountActiveByEmail(String email) {
        return getUserByEmail(email).isActive();
    }

    /**
     * Retourniert den zur übergebenen E-Mail zugehörigen Benutzer.
     *
     * @return - Benutzer zur E-Mail
     */
    public UserEntity getUserByEmail(String email) {
        Optional<UserEntity> entity = userRepository.findByEmail(email);

        if (!entity.isPresent()) {
            throw new UserNotFoundException();
        }
        return entity.get();
    }

    /**
     * Retourniert ob bereits ein Benutzerkonto mit der übergebenen E-Mail registriert ist oder nicht.
     *
     * @return - E-Mail bereits registriert (T/F)
     */
    public boolean isEmailAlreadyRegistered(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.isPresent();
    }

    /**
     * Registriert einen Benutzer und retourniert anschließend das persistierte Benutzer-Objekt.
     *
     * @return - Benutzer-Objekt
     */
    public UserEntity register(RegisterRequest request) {
        LocationEntity location = new LocationEntity();
        location.setPostal(request.getPostal());
        location.setLocation(request.getLocation());
        location.setCountry(countryService.getCountryById(request.getCountryId()));

        AddressEntity address = new AddressEntity();
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setDoor(request.getDoor());
        address.setStair(request.getStair());

        PersonEntity person = new PersonEntity();
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setBirthDate(request.getBirthDate());

        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(Boolean.TRUE);
        user.setRegistrationDate(Instant.now());
        user.setRole(roleService.getRoleByRoleName("Kunde"));

        user.setPerson(person);
        person.setAddress(address);
        address.setLocation(location);

        UserEntity persistedUser = userRepository.save(user);

        ShoppingCartEntity shoppingCart =
                new ShoppingCartEntity(personService.getPersonById(persistedUser.getPerson().getId()), 0);
        shoppingCartService.save(shoppingCart);

        return persistedUser;
    }

    /**
     * Retourniert das aktuelle Benutzer-Objekt aus dem Security-Context.
     *
     * @return - Aktueller Benutzer
     */
    public UserEntity getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return getUserByEmail(authentication.getName());
    }

    /**
     * Retourniert den zur übergebenen Person-ID zugehörigen Benutzer.
     *
     * @return - Benutzer zur Person-ID
     */
    public UserEntity getUserByPersonId(Long id) {

        Optional<UserEntity> entity = userRepository.findByPersonId(id);

        if (!entity.isPresent()) {
            throw new UserNotFoundException();
        }

        return entity.get();
    }

    /**
     * Aktualisiert das Datum der letzten Anmeldung des zur übergebenen E-Mail zugehörigen Benutzers
     */
    public void updateLastLogin(String username) {
        Optional<UserEntity> entity = userRepository.findByEmail(username);

        if (!entity.isPresent()) {
            throw new UserNotFoundException();
        }

        UserEntity user = entity.get();
        user.setLastLogin(Instant.now());

        save(user);
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }
}
