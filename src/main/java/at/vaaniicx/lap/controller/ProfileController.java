package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.*;
import at.vaaniicx.lap.model.request.profile.UpdateProfileRequest;
import at.vaaniicx.lap.model.response.profile.ModifyProfileResponse;
import at.vaaniicx.lap.service.*;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Blob;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ProfilePictureService profilePictureService;

    @Autowired
    private CountryService countryService;

    @PostMapping("/update")
    public ModifyProfileResponse updateProfile(@RequestBody @Validated UpdateProfileRequest request) {
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

        return ModifyProfileResponse.builder().id(user.getId()).email(user.getEmail()).build();
    }
}
