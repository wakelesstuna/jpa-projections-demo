package io.wakelesstuna.jpaprojectiondemo.services;

import io.wakelesstuna.jpaprojectiondemo.entities.Person;
import io.wakelesstuna.jpaprojectiondemo.repositories.PersonRepository;
import io.wakelesstuna.jpaprojectiondemo.repositories.projections.PersonView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepo;
    public Person getPersonByFirstName(final String firstName) {
        return personRepo.getByFirstName(firstName);
    }

    @Transactional
    public Person createPerson(final Person person) {
        return personRepo.save(person);
    }

    public PersonView getPersonViewByFirstName(String firstName) {
        return personRepo.findByFirstName(firstName);
    }
}
