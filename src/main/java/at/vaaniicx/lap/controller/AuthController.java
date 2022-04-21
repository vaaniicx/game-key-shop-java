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

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;
    private UserService userService;

    @GetMapping
    public ResponseEntity<AuthResponse> authenticateUser() {

        UserEntity userEntity;
        try {
            userEntity = userService.getCurrentUserEntity();
        } catch (UserNotFoundException e) {
            throw new InvalidTokenException();
        }

        return ResponseEntity.ok(new AuthResponse(UserResponseMapper.INSTANCE.entityToResponse(userEntity)));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtLoginResponse> loginUser(@RequestBody @Validated JwtLoginRequest request) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);

        if (!userService.isUserActive(userDetails.getUsername())) {
            throw new UserInactiveException();
        }
        userService.updateLastLogin(userDetails.getUsername());

        return ResponseEntity.ok(new JwtLoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody @Validated RegisterRequest request) {

        boolean alreadyRegistered = userService.isEmailAlreadyRegistered(request.getEmail());

        if (alreadyRegistered) {
            throw new UserExistsException();
        }

        UserEntity userEntity = userService.registerUser(request);

        return ResponseEntity.ok(new RegisterResponse(userEntity.getEmail(), userEntity.getRegistrationDate()));
    }
}
