package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<CountryEntity, Long> {

    @Override
    List<CountryEntity> findAll();

    @Override
    Optional<CountryEntity> findById(Long id);

    Optional<CountryEntity> findByCountry(String country);
}
