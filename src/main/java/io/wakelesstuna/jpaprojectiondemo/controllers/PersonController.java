package io.wakelesstuna.jpaprojectiondemo.controllers;

import io.wakelesstuna.jpaprojectiondemo.entities.File;
import io.wakelesstuna.jpaprojectiondemo.entities.Person;
import io.wakelesstuna.jpaprojectiondemo.repositories.projections.PersonView;
import io.wakelesstuna.jpaprojectiondemo.services.PersonService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/person")
public record PersonController(PersonService personService) {

    @GetMapping("/{firstName}")
    public ResponseEntity<Person> getPerson(@PathVariable final String firstName) {
        Person person = personService.getPersonByFirstName(firstName);
        return ResponseEntity.ok().body(person);
    }

    @GetMapping("view/{firstName}")
    public ResponseEntity<PersonView> getPersonView(@PathVariable final String firstName) {
        PersonView personView = personService.getPersonViewByFirstName(firstName);
        return ResponseEntity.ok().body(personView);
    }

    @PostMapping
    public ResponseEntity<Person> createUser(@RequestBody final Person person) {
        Person newPerson = personService.createPerson(person);
        return ResponseEntity.ok(newPerson);
    }
}
