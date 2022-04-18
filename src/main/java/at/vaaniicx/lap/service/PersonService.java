package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.PersonNotFoundException;
import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonEntity getPersonById(Long id){
        Optional<PersonEntity> entity = personRepository.findById(id);

        if (!entity.isPresent()) {
            throw new PersonNotFoundException();
        }

        return entity.get();
    }
}
