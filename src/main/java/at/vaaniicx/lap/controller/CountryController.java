package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.country.CountryResponseMapper;
import at.vaaniicx.lap.model.entity.CountryEntity;
import at.vaaniicx.lap.model.response.country.CountryResponse;
import at.vaaniicx.lap.service.CountryService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CountryResponse>> getAllCountries() {

        List<CountryResponse> countryResponses = countryService.getAllCountries()
                .stream()
                .map(countryMapper::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(countryResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getCountryById(@PathVariable("id") Long id) {

        CountryEntity countryById = countryService.getCountryById(id);

        return ResponseEntity.ok(countryMapper.entityToResponse(countryById));
    }
}
