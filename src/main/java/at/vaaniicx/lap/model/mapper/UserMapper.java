package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.ProfilePictureDTO;
import at.vaaniicx.lap.model.dto.UserDTO;
import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.request.RegisterRequest;
import at.vaaniicx.lap.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Component
public class UserMapper {

    private static RoleService roleService;
    private static PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService0;

    @Autowired
    private PasswordEncoder passwordEncoder0;

    @PostConstruct
    private void initStaticFields() {
        roleService = this.roleService0;
        passwordEncoder = this.passwordEncoder0;
    }

    public static UserEntity toEntity(RegisterRequest request) {

        RoleEntity role = roleService.getRoleByRoleName("Kunde");

        return new UserEntity(request.getEmail(), passwordEncoder.encode(request.getPassword()), true,
                Instant.now(), role);
    }

    public static UserDTO toDto(UserEntity e) {
        return new UserDTO(e.getId(), e.getEmail(), e.isActive(), e.getRegistrationDate(), e.getLastLogin(),
                PersonMapper.toDto(e.getPerson()), RoleMapper.toDto(e.getRole()),
                ProfilePictureMapper.toDto(e.getProfilePicture()));
    }
}
