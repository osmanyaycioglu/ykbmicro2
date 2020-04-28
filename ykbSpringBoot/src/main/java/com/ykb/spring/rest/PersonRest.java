package com.ykb.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ykb.spring.model.Person;
import com.ykb.spring.model.PersonManager;

@RestController
@RequestMapping("/v1/person")
public class PersonRest {

    @Autowired
    private PersonManager pm;

    @PutMapping
    public String addPerson(@RequestBody final Person person) {
        this.pm.addPerson(person);
        return "OK";
    }

    @PutMapping("/addExtra")
    public String addPerson2(@RequestBody final Person person) {
        this.pm.addPerson(person);
        return "OK";
    }

    @GetMapping("/{email}")
    public Person getPerson(@PathVariable("email") final String email) {
        return this.pm.getPerson(email);
    }

    @PutMapping("/all")
    public List<Person> getAllPersons() {
        return this.pm.getAllPersons();
    }

}
