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
    private PersonMapper pMapper;

    public Person findPerson(int id) {
        return pMapper.findPerson(id);
    }
}
