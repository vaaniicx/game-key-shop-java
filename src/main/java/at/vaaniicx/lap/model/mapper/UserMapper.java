package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.request.RegisterRequest;
import at.vaaniicx.lap.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserMapper {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity toEntity(RegisterRequest request) {

        RoleEntity role = roleService.getRoleByRoleName("Kunde");

        return new UserEntity(request.getEmail(), passwordEncoder.encode(request.getPassword()), true,
                Instant.now(), role);
    }
}
