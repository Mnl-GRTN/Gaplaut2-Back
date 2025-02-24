package org.example.controller;

import org.example.service.Centre;
import org.example.service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class CentreRestController {
    
    @Autowired
    private CentreService service;

    @PostMapping(path = "/public/api/centres")
    public void create(@RequestBody Centre c) throws URISyntaxException{
        service.create(c);
    }

    @GetMapping(path = "/public/api/centres")
    public Iterable<Centre> read(){
        return service.readAll();
    }

    @GetMapping(path = "/public/api/centre/{id}")
    public Centre readOne(@PathVariable("id") int id){
        return service.readOne(id);
    }

}
