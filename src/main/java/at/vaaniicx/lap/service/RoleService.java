package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.role.RoleNotFoundException;
import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.repository.RoleRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public RoleEntity getRoleById(Long id) {
        Optional<RoleEntity> entity = roleRepository.findById(id);

        if (!entity.isPresent()) {
            throw new RoleNotFoundException();
        }
        return entity.get();
    }

    public RoleEntity getRoleByRoleName(String roleName) {
        Optional<RoleEntity> entity = roleRepository.findByRole(roleName);

        if (!entity.isPresent()) {
            throw new RoleNotFoundException();
        }
        return entity.get();
    }

    public RoleEntity updateRole(RoleEntity e) {
        return roleRepository.save(e);
    }

    public RoleEntity registerRole(RoleEntity e) {
        return roleRepository.save(e);
    }

    public boolean deleteRole(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RoleNotFoundException();
        }

        return !roleRepository.existsById(id);
    }
}
