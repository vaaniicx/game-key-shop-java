package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.DeveloperDTO;
import at.vaaniicx.lap.model.mapper.DeveloperMapper;
import at.vaaniicx.lap.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @GetMapping
    @ResponseBody
    public List<DeveloperDTO> getAll() {
        return developerService.getAllDeveloper().stream().map(DeveloperMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DeveloperDTO getById(@PathVariable("id") Long developerId) {
        return DeveloperMapper.toDto(developerService.getDeveloperById(developerId));
    }
}
