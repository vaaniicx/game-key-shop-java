package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.RoleEntity;
import at.vaaniicx.lap.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public RoleEntity findById(@PathVariable Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
