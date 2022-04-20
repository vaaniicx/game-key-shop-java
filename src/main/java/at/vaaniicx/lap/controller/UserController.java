package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.user.UserResponseMapper;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.response.role.UserByRoleResponse;
import at.vaaniicx.lap.model.response.user.UserResponse;
import at.vaaniicx.lap.service.UserService;
import org.mapstruct.factory.Mappers;
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
    private final UserResponseMapper userMapper;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userMapper = Mappers.getMapper(UserResponseMapper.class);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser() {

        List<UserResponse> userResponses = userService.getAllUsers()
                .stream()
                .map(userMapper::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId) {

        UserEntity user = userService.getUserById(userId);

        return ResponseEntity.ok(userMapper.entityToResponse(user));
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<List<UserByRoleResponse>> getUserByRole(@PathVariable("id") Long roleId) {

        List<UserByRoleResponse> userByRoleResponses = userService.findUserByRoleId(roleId)
                .stream()
                .map(e -> UserByRoleResponse.builder().id(e.getId()).email(e.getEmail()).active(e.isActive()).build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(userByRoleResponses);
    }
}
