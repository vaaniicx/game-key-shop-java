package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.role.RoleNotFoundException;
import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Retourniert alle Rollen.
     *
     * @return - Liste aller Rollen
     */
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

    /**
     * Retourniert die zum übergebenen Rollennamen zugehörige Rolle.
     *
     * @return - Veräffentlicher zur übergebenen ID
     */
    public RoleEntity getRoleByRoleName(String role) {
        Optional<RoleEntity> entity = roleRepository.findByRole(role);

        if (!entity.isPresent()) {
            throw new RoleNotFoundException();
        }
        return entity.get();
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public RoleEntity save(RoleEntity entity) {
        return roleRepository.save(entity);
    }

    /**
     * Löscht das Entity-Objekt zur übergebenen ID.
     *
     * @param id - ID des zu löschenden Objekt
     */
    public boolean deleteById(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RoleNotFoundException();
        }

        return !roleRepository.existsById(id);
    }
}
