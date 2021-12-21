package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.CountryDTO;
import at.vaaniicx.lap.model.entity.CountryEntity;
import at.vaaniicx.lap.model.mapper.CountryMapper;
import at.vaaniicx.lap.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper mapper;

    @GetMapping
    @ResponseBody
    public List<CountryDTO> getAll() {
        return countryRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CountryEntity getById(@PathVariable Long id) {
        return countryRepository.findById(id).orElse(null);
    }
}
