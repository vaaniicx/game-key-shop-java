package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.*;
import at.vaaniicx.lap.model.request.profile.ChangePasswordRequest;
import at.vaaniicx.lap.model.request.profile.DeactivateProfileRequest;
import at.vaaniicx.lap.model.request.profile.UpdateProfileRequest;
import at.vaaniicx.lap.model.response.profile.ModifyProfileResponse;
import at.vaaniicx.lap.service.CountryService;
import at.vaaniicx.lap.service.ProfilePictureService;
import at.vaaniicx.lap.service.RoleService;
import at.vaaniicx.lap.service.UserService;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final RoleService roleService;
    private final ProfilePictureService profilePictureService;
    private final CountryService countryService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileController(UserService userService, RoleService roleService, ProfilePictureService profilePictureService,
                             CountryService countryService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.profilePictureService = profilePictureService;
        this.countryService = countryService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/update")
    public ResponseEntity<ModifyProfileResponse> updateProfile(@RequestBody @Validated UpdateProfileRequest request) {

        UserEntity user = userService.getUserById(request.getId());
        user.setRole(roleService.getRoleById(request.getRoleId()));
        user.setActive(request.isActive());

        PersonEntity person = user.getPerson();
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setBirthDate(request.getBirthDate());

        AddressEntity address = person.getAddress();
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setDoor(request.getDoor());
        address.setStair(request.getStair());

        LocationEntity location = address.getLocation();
        location.setLocation(request.getLocation());

        CountryEntity country = countryService.getCountryById(request.getCountryId());

        if (request.getProfilePicture() != null) {
            Blob blob = ImageConversionHelper.byteArrayToBlob(request.getProfilePicture());

            ProfilePictureEntity profilePicture = user.getProfilePicture();
            if (profilePicture != null) {
                profilePicture.setPicture(blob);
            } else {
                profilePicture = ProfilePictureEntity.builder().picture(blob).build();
            }

            user.setProfilePicture(profilePictureService.save(profilePicture));
        }

        location.setCountry(country);
        address.setLocation(location);
        person.setAddress(address);
        user.setPerson(person);

        userService.save(user);

        return ResponseEntity.ok(new ModifyProfileResponse(user.getId(), user.getEmail()));
    }

    @PutMapping("/deactivate")
    public ResponseEntity<Boolean> deactivateProfile(@RequestBody @Validated DeactivateProfileRequest request) {

        UserEntity user = userService.getUserById(request.getUserId());
        user.setActive(false);

        userService.save(user);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody @Validated ChangePasswordRequest request) {

        UserEntity user = userService.getUserById(request.getUserId());

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            // passwörter stimmen nicht überein
            return new ResponseEntity<>(HttpStatus.OK);
        }

        if (request.getNewPassword().equals(request.getNewPasswordRepeated())) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userService.save(user);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
