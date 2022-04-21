package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.person.PersonNotFoundException;
import at.vaaniicx.lap.model.entity.PersonEntity;
import at.vaaniicx.lap.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Retourniert die zur übergebenen ID zugehörige Person.
     *
     * @return - Person zur übergebenen ID
     */
    public PersonEntity getPersonById(Long id){
        Optional<PersonEntity> entity = personRepository.findById(id);

        if (!entity.isPresent()) {
            throw new PersonNotFoundException();
        }

        return entity.get();
    }
}
