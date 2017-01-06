package com.spring.hello.service;

import com.spring.hello.mapper.PersonMapper;
import com.spring.hello.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ustc-lezg on 05/01/2017.
 */
@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    public Person findPerson(int id) {
        return personMapper.findPerson(id);
    }

    public int savePerson(Person p) {
        return personMapper.savePerson(p);
    }
}
