package org.example.restfullservice.controller;


import org.example.restfullservice.Greeting;
import org.example.restfullservice.model.Client;
import org.example.restfullservice.service.ClientService;
import org.example.restfullservice.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    private final ClientService clientService;
    @Autowired
    public MainController(ClientService clientService){
        this.clientService = clientService;
    }
    @PostMapping("/clients")
    public ResponseEntity<?>create(@RequestBody Client client){
        clientService.createClient(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> read(){
        final List<Client> clients = clientService.clientAll();
        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id){
        final Client client = clientService.read(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client){
        final boolean updated = clientService.update(client, id);
        return updated
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id")int id){
        final boolean deleted = clientService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
