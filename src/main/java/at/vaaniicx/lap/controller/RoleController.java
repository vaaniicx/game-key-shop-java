package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.role.DeleteRoleException;
import at.vaaniicx.lap.mapper.role.RoleResponseMapper;
import at.vaaniicx.lap.mapper.user.UserResponseMapper;
import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.model.request.role.RegisterRoleRequest;
import at.vaaniicx.lap.model.request.role.UpdateRoleRequest;
import at.vaaniicx.lap.model.response.role.RoleResponse;
import at.vaaniicx.lap.model.response.user.SlimUserResponse;
import at.vaaniicx.lap.service.RoleService;
import at.vaaniicx.lap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    /**
     * Liefer alle Rollen.
     *
     * @return - Liste aller Rollen
     */
    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {

        List<RoleResponse> roleResponses = roleService.getAllRoles()
                .stream()
                .map(RoleResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(roleResponses);
    }

    /**
     * Liefert die Rolle zur ID.
     *
     * @param roleId - ID zur Rolle
     * @return - Rolle zur ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable("id") Long roleId) {

        RoleEntity roleById = roleService.getRoleById(roleId);

        return ResponseEntity.ok(RoleResponseMapper.INSTANCE.entityToResponse(roleById));
    }

    /**
     * Liefer alle Benutzer zu einer Rolle.
     *
     * @param roleId - ID zur Rolle
     * @return - Liste aller Benutzer zu einer Rolle
     */
    @GetMapping("/{id}/user")
    public ResponseEntity<List<SlimUserResponse>> getUserByRoleId(@PathVariable("id") Long roleId) {

        List<SlimUserResponse> responses = userService.getUserByRoleId(roleId)
                .stream()
                .map(UserResponseMapper.INSTANCE::entityToSlimResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Registriert eine Rolle.
     *
     * @param request - Registrierungs-Request
     * @return - Registrierte Rolle
     */
    @PostMapping("/register")
    public ResponseEntity<RoleEntity> registerRole(@RequestBody @Validated RegisterRoleRequest request) {

        RoleEntity role = new RoleEntity(); // Erstellen
        role.setRole(request.getRole());

        RoleEntity persistedRole = roleService.save(role); // Persistieren

        return ResponseEntity.ok(persistedRole);
    }

    /**
     * Aktualisiert eine Rolle.
     *
     * @param request - Aktualisierungs-Request
     * @return - Aktualisierte Rolle
     */
    @PostMapping("/update")
    public ResponseEntity<RoleEntity> updateRole(@RequestBody @Validated UpdateRoleRequest request) {

        RoleEntity role = roleService.getRoleById(request.getId());
        role.setRole(request.getRole()); // Neue Werte setzen

        RoleEntity updatedRole = roleService.save(role); // Persistieren

        return ResponseEntity.ok(updatedRole);
    }

    /**
     * Löscht die Rolle zur ID
     *
     * @param id - ID zur Rolle
     * @return - Response-Entity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) {

        boolean isRuleSet = userService.getAllUsers().stream().anyMatch(user -> user.getRole().getId() == id);

        // Ist die Rolle einem oder mehreren Benutzern zugeordnet?
        if (!isRuleSet) {
            roleService.deleteById(id); // Nicht in Verwendung, kann gelöscht werden
        } else {
            throw new DeleteRoleException(); // In Verwendung, kann nicht gelöscht werden
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
