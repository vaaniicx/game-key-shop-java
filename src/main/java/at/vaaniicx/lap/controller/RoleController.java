package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.RoleDTO;
import at.vaaniicx.lap.model.mapper.RoleMapper;
import at.vaaniicx.lap.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper mapper;

    @GetMapping
    public List<RoleDTO> getAll() {
        return roleService.getAllRoles().stream().map(RoleMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoleDTO getById(@PathVariable Long id) {
        return RoleMapper.toDto(roleService.getRoleById(id));
    }
}
