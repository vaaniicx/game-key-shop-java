package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.user.UserResponseMapper;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.response.user.UserResponse;
import at.vaaniicx.lap.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserResponseMapper userMapper;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userMapper = Mappers.getMapper(UserResponseMapper.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId) {

        UserEntity user = userService.getUserById(userId);

        return new ResponseEntity<>(userMapper.entityToResponse(user), HttpStatus.OK);
    }
}
