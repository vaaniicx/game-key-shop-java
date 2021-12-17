package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.RoleNotFoundException;
import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

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
        Optional<RoleEntity> entity = roleRepository.findByRoleName(roleName);

        if (!entity.isPresent()) {
            throw new RoleNotFoundException();
        }
        return entity.get();
    }
}
