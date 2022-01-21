package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.UserDTO;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.mapper.UserMapper;
import at.vaaniicx.lap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) {

        UserEntity user = userService.getUserById(userId);

        return new ResponseEntity<>(UserMapper.toDto(user), HttpStatus.OK);
    }
}
