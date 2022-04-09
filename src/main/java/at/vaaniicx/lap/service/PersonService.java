package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.PersonNotFoundException;
import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonEntity getPersonById(Long id){
        Optional<PersonEntity> entity = repository.findById(id);

        if (!entity.isPresent()) {
            throw new PersonNotFoundException();
        }

        return entity.get();
    }
}
