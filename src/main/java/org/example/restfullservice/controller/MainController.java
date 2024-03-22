package org.example.restfullservice.controller;


import org.example.restfullservice.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MainController {

    private static final String templates = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting (@RequestParam(value = "name", defaultValue = "World")String name){
        return new Greeting(counter.incrementAndGet(), String.format(templates, name));
    }
    @GetMapping("/")
    String hello(){
        return "Welcome to my RESTful web service!";
    }

}
