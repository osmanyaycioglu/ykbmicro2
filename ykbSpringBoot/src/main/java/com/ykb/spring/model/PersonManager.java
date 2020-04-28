package com.ykb.spring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service
// @Scope("prototype")
public class PersonManager {

    private final Map<String, Person> personMap = new HashMap<>();


    public void addPerson(final Person person) {
        this.personMap.put(person.getEmail(),
                           person);
    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(this.personMap.values());
    }

    public Person getPerson(final String emailParam) {
        return this.personMap.get(emailParam);
    }
}
