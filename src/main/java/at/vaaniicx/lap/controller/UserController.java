package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.user.UserResponseMapper;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.response.role.UserByRoleResponse;
import at.vaaniicx.lap.model.response.user.UserResponse;
import at.vaaniicx.lap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Liefert alle Benutzerkonten.
     *
     * @return - Liste aller Benutzerkonten
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser() {

        List<UserResponse> userResponses = userService.getAllUsers()
                .stream()
                .map(UserResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponses);
    }

    /**
     * Liefert das Benutzerkonto zur ID.
     *
     * @param userId - ID zum Benutzer
     * @return - Benutzer zur ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId) {

        UserEntity user = userService.getUserById(userId);

        return ResponseEntity.ok(UserResponseMapper.INSTANCE.entityToResponse(user));
    }

    /**
     * Liefert alle Benutzer zu einer Rolle.
     *
     * @param roleId - ID zur Rolle
     * @return - Liste aller Benutzer einer Rolle
     */
    @GetMapping("/role/{id}")
    public ResponseEntity<List<UserByRoleResponse>> getUserByRole(@PathVariable("id") Long roleId) {

        List<UserByRoleResponse> userByRoleResponses = userService.getUserByRoleId(roleId)
                .stream()
                .map(e -> UserByRoleResponse.builder().id(e.getId()).email(e.getEmail()).active(e.isActive()).build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(userByRoleResponses);
    }
}
