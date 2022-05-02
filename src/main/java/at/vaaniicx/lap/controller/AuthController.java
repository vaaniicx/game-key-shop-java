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
import org.springframework.beans.factory.annotation.Autowired;
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
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                          UserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    /**
     * Authentifiziert den aktuellen Benutzer.
     *
     * @return - Benutzerobjekt
     */
    @GetMapping
    public ResponseEntity<AuthResponse> authenticateUser() {

        UserEntity userEntity;
        try {
            // hole aktuellen Benutzer
            userEntity = userService.getCurrentUserEntity();
        } catch (UserNotFoundException e) {
            throw new InvalidTokenException(); // Token nicht gültig
        }

        return ResponseEntity.ok(new AuthResponse(UserResponseMapper.INSTANCE.entityToResponse(userEntity)));
    }

    /**
     * Führt das Benutzer-Login durch.
     *
     * @param request - Login-Request
     * @return - Auth-Token
     */
    @PostMapping("/login")
    public ResponseEntity<JwtLoginResponse> loginUser(@RequestBody @Validated JwtLoginRequest request) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authToken); // Authentifizierung

        // Benutzer laden
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails); // Token-Generierung

        // Benutzerkonto deaktiviert?
        if (!userService.isAccountActiveByEmail(userDetails.getUsername())) {
            throw new UserInactiveException(); // Konto deaktiviert, keine Anmeldung möglich
        }
        // Anmeldung erfolgreich, letzte Anmeldung aktualisieren
        userService.updateLastLogin(userDetails.getUsername());

        return ResponseEntity.ok(new JwtLoginResponse(token));
    }

    /**
     * Registriert ein Benutzerkonto.
     *
     * @param request - Registrierungs-Request
     * @return - Registrierungs-Response
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody @Validated RegisterRequest request) {

        // Wurde die E-Mail bereits registriert?
        boolean alreadyRegistered = userService.isEmailAlreadyRegistered(request.getEmail());

        if (alreadyRegistered) {
            throw new UserExistsException(); // E-Mail bereits registriert, keine Registrierung möglich
        }

        UserEntity userEntity = userService.register(request); // Registrierung durchführen

        return ResponseEntity.ok(new RegisterResponse(userEntity.getEmail(), userEntity.getRegistrationDate()));
    }
}
