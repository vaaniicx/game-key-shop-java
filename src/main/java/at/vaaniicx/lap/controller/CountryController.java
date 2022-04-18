package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.country.CountryResponseMapper;
import at.vaaniicx.lap.model.response.country.CountryResponse;
import at.vaaniicx.lap.service.CountryService;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;
    private final CountryResponseMapper countryMapper;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
        this.countryMapper = Mappers.getMapper(CountryResponseMapper.class);
    }

    @GetMapping
    @ResponseBody
    public List<CountryResponse> getAll() {
        return countryService.getAllCountries().stream().map(c -> new CountryResponse(c.getId(), c.getCountry())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CountryResponse getById(@PathVariable("id") Long countryId) {
        return countryMapper.entityToResponse(countryService.getCountryById(countryId));
    }
}
