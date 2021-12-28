package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.CountryDTO;
import at.vaaniicx.lap.model.mapper.CountryMapper;
import at.vaaniicx.lap.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    @ResponseBody
    public List<CountryDTO> getAll() {
        return countryService.getAllCountries().stream().map(CountryMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CountryDTO getById(@PathVariable Long id) {
        return CountryMapper.toDto(countryService.getCountryById(id));
    }
}
