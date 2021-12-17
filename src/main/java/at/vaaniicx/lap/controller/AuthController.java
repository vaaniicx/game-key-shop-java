package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.UserExistsException;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.request.JwtLoginRequest;
import at.vaaniicx.lap.model.request.RegisterRequest;
import at.vaaniicx.lap.model.response.JwtLoginResponse;
import at.vaaniicx.lap.model.response.RegisterResponse;
import at.vaaniicx.lap.security.jwt.JwtTokenUtil;
import at.vaaniicx.lap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtLoginResponse> login(@RequestBody @Validated JwtLoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtLoginResponse(token));
    }


    @PostMapping("/auth/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Validated RegisterRequest request) {
        if (userService.isEmailAlreadyRegistered(request.getEmail())) {
            throw new UserExistsException();
        }
        UserEntity userEntity = userService.registerUser(request);
        return ResponseEntity.ok(new RegisterResponse(userEntity.getEmail(), userEntity.getRegistrationDate()));
    }
}
