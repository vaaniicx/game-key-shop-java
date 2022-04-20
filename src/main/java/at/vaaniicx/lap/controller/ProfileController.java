package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.*;
import at.vaaniicx.lap.model.request.profile.UpdateProfileRequest;
import at.vaaniicx.lap.model.response.profile.ModifyProfileResponse;
import at.vaaniicx.lap.service.CountryService;
import at.vaaniicx.lap.service.ProfilePictureService;
import at.vaaniicx.lap.service.UserService;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final ProfilePictureService profilePictureService;
    private final CountryService countryService;

    public ProfileController(UserService userService, ProfilePictureService profilePictureService,
                             CountryService countryService) {
        this.userService = userService;
        this.profilePictureService = profilePictureService;
        this.countryService = countryService;
    }

    @PostMapping("/update")
    public ResponseEntity<ModifyProfileResponse> updateProfile(@RequestBody @Validated UpdateProfileRequest request) {

        UserEntity user = userService.getUserById(request.getId());

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

        userService.saveUser(user);

        return ResponseEntity.ok(new ModifyProfileResponse(user.getId(), user.getEmail()));
    }

    @GetMapping("/{id}/deactivate")
    public ResponseEntity<Boolean> deactivateAccount(@PathVariable("id") Long userId) {

        UserEntity user = userService.getUserById(userId);
        user.setActive(false);

        userService.saveUser(user);

        return ResponseEntity.ok(Boolean.TRUE);
    }
}
