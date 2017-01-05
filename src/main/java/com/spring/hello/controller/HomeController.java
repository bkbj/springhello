package com.spring.hello.controller;

import com.spring.hello.model.Message;
import com.spring.hello.model.Person;
import com.spring.hello.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ustc-lezg on 04/01/2017.
 */

@RestController
public class HomeController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome Spring 世界";
    }

    @GetMapping("/message/{player}")
    public Message getMessage(@PathVariable String player) {
        return new Message(player, "Hello " + player);
    }

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable int id) {
        return personService.findPerson(id);
    }
}
