package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Override
    List<PersonEntity> findAll();

    @Override
    Optional<PersonEntity> findById(Long id);
}
