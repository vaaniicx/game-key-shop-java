package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.*;
import at.vaaniicx.lap.model.request.profile.UpdateProfileRequest;
import at.vaaniicx.lap.model.response.profile.ModifyProfileResponse;
import at.vaaniicx.lap.service.AddressService;
import at.vaaniicx.lap.service.PersonService;
import at.vaaniicx.lap.service.ProfilePictureService;
import at.vaaniicx.lap.service.UserService;
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

    @PostMapping("/update")
    public ModifyProfileResponse updateProfile(@RequestBody @Validated UpdateProfileRequest request) {
        UserEntity entity = userService.getUserById(request.getId());
        entity.setEmail(request.getEmail());

        if (request.getProfilePicture() != null) {
            Blob blob = ImageConversionHelper.byteArrayToBlob(request.getProfilePicture());

            ProfilePictureEntity profilePicture = entity.getProfilePicture();
            if (profilePicture != null) {
                profilePicture.setPicture(blob);
            } else {
                profilePicture = ProfilePictureEntity.builder().picture(blob).build();
            }

            entity.setProfilePicture(profilePictureService.save(profilePicture));
        }

        userService.saveUser(entity);

        return ModifyProfileResponse.builder().id(entity.getId()).email(entity.getEmail()).build();
    }
}
