package com.spring.hello.controller;

import com.spring.hello.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ustc-lezg on 04/01/2017.
 */

@RestController
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome SpringHello";
    }

    @GetMapping("/message/{player}")
    public Message getMessage(@PathVariable String player) {
        return new Message(player, "Hello " + player);
    }
}
