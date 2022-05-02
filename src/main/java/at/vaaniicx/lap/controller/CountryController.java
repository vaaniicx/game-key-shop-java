package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.country.CountryResponseMapper;
import at.vaaniicx.lap.model.entity.CountryEntity;
import at.vaaniicx.lap.model.response.country.CountryResponse;
import at.vaaniicx.lap.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Liefert alle Länder.
     *
     * @return - Liste aller Länder
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CountryResponse>> getAllCountries() {

        List<CountryResponse> countryResponses = countryService.getAllCountries()
                .stream()
                .map(CountryResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(countryResponses);
    }

    /**
     * Liefert das Land zur ID.
     *
     * @param id - ID des Landes
     * @return - Land zur ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getCountryById(@PathVariable("id") Long id) {

        CountryEntity countryById = countryService.getCountryById(id);

        return ResponseEntity.ok(CountryResponseMapper.INSTANCE.entityToResponse(countryById));
    }
}
