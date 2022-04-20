package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.role.RoleResponseMapper;
import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.model.request.management.role.RegisterRoleRequest;
import at.vaaniicx.lap.model.request.management.role.UpdateRoleRequest;
import at.vaaniicx.lap.model.response.role.RoleResponse;
import at.vaaniicx.lap.service.RoleService;
import org.mapstruct.factory.Mappers;
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
    private final RoleResponseMapper roleMapper;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
        this.roleMapper = Mappers.getMapper(RoleResponseMapper.class);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {

        List<RoleResponse> roleResponses = roleService.getAllRoles()
                .stream()
                .map(roleMapper::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(roleResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable("id") Long roleId) {

        RoleEntity roleById = roleService.getRoleById(roleId);

        return ResponseEntity.ok(roleMapper.entityToResponse(roleById));
    }

    @PostMapping("/register")
    public ResponseEntity<RoleEntity> registerRole(@RequestBody @Validated RegisterRoleRequest request) {

        RoleEntity role = new RoleEntity();
        role.setRole(request.getRole());

        RoleEntity persistedRole = roleService.saveRole(role);

        return ResponseEntity.ok(persistedRole);
    }

    @PostMapping("/update")
    public ResponseEntity<RoleEntity> updateRole(@RequestBody @Validated UpdateRoleRequest request) {

        RoleEntity role = roleService.getRoleById(request.getId());
        role.setRole(request.getRole());

        RoleEntity updatedRole = roleService.saveRole(role);

        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable("id") Long id) {

        roleService.deleteRole(id);

        return ResponseEntity.ok(Boolean.TRUE);
    }
}
