package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.country.CountryNotFoundException;
import at.vaaniicx.lap.model.entity.CountryEntity;
import at.vaaniicx.lap.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public CountryEntity getCountryById(Long id) {
        Optional<CountryEntity> entity = countryRepository.findById(id);

        if (!entity.isPresent()) {
            throw new CountryNotFoundException();
        }
        return entity.get();
    }

    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }
}
