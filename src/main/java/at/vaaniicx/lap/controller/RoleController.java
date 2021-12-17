package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.RoleDTO;
import at.vaaniicx.lap.model.entity.RoleEntity;
import at.vaaniicx.lap.model.mapper.RoleMapper;
import at.vaaniicx.lap.repository.RoleRepository;
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
    public List<RoleDTO> findAll() {
        return roleService.getAllRoles().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoleDTO findById(@PathVariable Long id) {
        return mapper.toDto(roleService.getRoleById(id));
    }
}
