package io.wakelesstuna.jpaprojectiondemo.repositories;

import io.wakelesstuna.jpaprojectiondemo.entities.Person;
import io.wakelesstuna.jpaprojectiondemo.repositories.projections.PersonView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {

    PersonView findByFirstName(String firstName);
    Person getByFirstName(String firstName);
}
