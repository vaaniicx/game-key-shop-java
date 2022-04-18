package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.auth.InvalidTokenException;
import at.vaaniicx.lap.exception.user.UserExistsException;
import at.vaaniicx.lap.exception.user.UserInactiveException;
import at.vaaniicx.lap.exception.user.UserNotFoundException;
import at.vaaniicx.lap.mapper.user.UserResponseMapper;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.request.JwtLoginRequest;
import at.vaaniicx.lap.model.request.RegisterRequest;
import at.vaaniicx.lap.model.response.auth.AuthResponse;
import at.vaaniicx.lap.model.response.auth.JwtLoginResponse;
import at.vaaniicx.lap.model.response.auth.RegisterResponse;
import at.vaaniicx.lap.security.jwt.JwtTokenUtil;
import at.vaaniicx.lap.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserResponseMapper userMapper;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                          UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = Mappers.getMapper(UserResponseMapper.class);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtLoginResponse> login(@RequestBody @Validated JwtLoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);

        if (!userService.isUserActive(userDetails.getUsername())) {
            throw new UserInactiveException();
        }

        userService.updateLastLogin(userDetails.getUsername());

        return ResponseEntity.ok(new JwtLoginResponse(token));
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Validated RegisterRequest request) {
        if (userService.isEmailAlreadyRegistered(request.getEmail())) {
            throw new UserExistsException();
        }
        UserEntity userEntity = userService.registerUser(request);
        return ResponseEntity.ok(new RegisterResponse(userEntity.getEmail(), userEntity.getRegistrationDate()));
    }

    @GetMapping
    public ResponseEntity<AuthResponse> auth() {
        UserEntity userEntity;
        try {
            userEntity = userService.getCurrentUserEntity();
        } catch (UserNotFoundException e) {
            throw new InvalidTokenException();
        }

        return ResponseEntity.ok(new AuthResponse(userMapper.entityToResponse(userEntity)));
    }
}
