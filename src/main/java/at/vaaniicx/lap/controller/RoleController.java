package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.role.RoleResponseMapper;
import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.model.request.management.role.RegisterRoleRequest;
import at.vaaniicx.lap.model.request.management.role.UpdateRoleRequest;
import at.vaaniicx.lap.model.response.role.RoleResponse;
import at.vaaniicx.lap.service.RoleService;
import org.mapstruct.factory.Mappers;
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

    @Autowired
    private RoleService roleService;

    private RoleResponseMapper roleMapper = Mappers.getMapper(RoleResponseMapper.class);

    @GetMapping
    public List<RoleResponse> getAll() {
        return roleService.getAllRoles().stream().map(roleMapper::entityToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoleResponse getById(@PathVariable("id") Long roleId) {
        return roleMapper.entityToResponse(roleService.getRoleById(roleId));
    }

    @PostMapping("/register")
    public RoleEntity registerRole(@RequestBody @Validated RegisterRoleRequest request) {
        RoleEntity role = RoleEntity.builder().role(request.getRole()).build();

        return roleService.updateRole(role);
    }

    @PostMapping("/update")
    public RoleEntity updateRole(@RequestBody @Validated UpdateRoleRequest request) {
        RoleEntity role = roleService.getRoleById(request.getId());
        role.setRole(request.getRole());

        return roleService.registerRole(role);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable("id") Long id) {
        boolean deleted = roleService.deleteRole(id);

        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
