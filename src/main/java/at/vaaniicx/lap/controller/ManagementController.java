package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.response.*;
import at.vaaniicx.lap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private GameService gameService;

    @GetMapping("/user")
    public List<UserManagementDataResponse> getUserManagementData() {
        List<UserManagementDataResponse> ret = new ArrayList<>();

        userService.getAllUsers().stream().filter(Objects::nonNull)
                .forEach(u -> ret.add(new UserManagementDataResponse(u.getId(), u.getEmail(), u.getRole().getId(),
                        u.getRole().getRole(), u.isActive(), u.getRegistrationDate(), u.getLastLogin())));

        return ret;
    }

    @GetMapping("/role")
    public List<RoleManagementDataResponse> getRoleManagementData() {
        List<RoleManagementDataResponse> ret = new ArrayList<>();

        roleService.getAllRoles().stream().filter(Objects::nonNull)
                .forEach(r -> ret.add(new RoleManagementDataResponse(r.getId(), r.getRole())));

        return ret;
    }

    @GetMapping("/address")
    public List<AddressManagementDataResponse> getAddressManagementData() {
        List<AddressManagementDataResponse> ret = new ArrayList<>();

        addressService.getAllAddresses().stream().filter(Objects::nonNull)
                .forEach(a -> ret.add(new AddressManagementDataResponse(a.getId(), a.getStreet(), a.getHouseNumber(),
                        a.getStair(), a.getDoor(), a.getLocation().getId(), a.getLocation().getLocation(),
                        a.getLocation().getPostal(), a.getLocation().getCountry().getId(),
                        a.getLocation().getCountry().getCountry())));

        return ret;
    }

    @GetMapping("/category")
    public List<CategoryManagementDataResponse> getCategoryManagementData() {
        List<CategoryManagementDataResponse> ret = new ArrayList<>();

        categoryService.getAllCategories().stream().filter(Objects::nonNull)
                .forEach(c ->
                        ret.add(new CategoryManagementDataResponse(c.getId(), c.getCategory(), c.getDescription())));

        return ret;
    }

    @GetMapping("/developer")
    public List<DeveloperManagementDataResponse> getDeveloperManagementData() {
        List<DeveloperManagementDataResponse> ret = new ArrayList<>();

        developerService.getAllDeveloper().stream().filter(Objects::nonNull)
                .forEach(d -> ret.add(new DeveloperManagementDataResponse(d.getId(), d.getDeveloper())));

        return ret;
    }

    @GetMapping("/publisher")
    public List<PublisherManagementDataResponse> getPublisherManagementData() {
        List<PublisherManagementDataResponse> ret = new ArrayList<>();

        publisherService.getAllPublisher().stream().filter(Objects::nonNull)
                .forEach(p -> ret.add(new PublisherManagementDataResponse(p.getId(), p.getPublisher())));

        return ret;
    }

    @GetMapping("/game")
    public List<GameManagementDataResponse> getGameManagementData() {
        List<GameManagementDataResponse> ret = new ArrayList<>();

        gameService.getAllGames().stream().filter(Objects::nonNull)
                .forEach(g -> ret.add(new GameManagementDataResponse(g.getId(), g.getTitle(), g.getDeveloper().getId(),
                        g.getDeveloper().getDeveloper(), g.getPublisher().getId(), g.getPublisher().getPublisher(),
                        g.getReleaseDate(), g.getOriginalPrice(), g.getPrice(), g.getAgeRestriction())));

        return ret;
    }
}
