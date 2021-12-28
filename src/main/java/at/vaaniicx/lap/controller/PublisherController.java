package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.PublisherDTO;
import at.vaaniicx.lap.model.mapper.PublisherMapper;
import at.vaaniicx.lap.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherRepository repository;

    @GetMapping
    @ResponseBody
    public List<PublisherDTO> getAll() {
        return repository.findAll().stream().map(PublisherMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PublisherDTO getById(@PathVariable Long id) {
        return PublisherMapper.toDto(Objects.requireNonNull(repository.findById(id).orElse(null)));
    }
}
