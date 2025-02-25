package org.example.controller;

import org.example.service.Centre;
import org.example.service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class CentreRestController {
    
    @Autowired
    private CentreService service;

    @PreAuthorize("hasAuthority('ROLE_superadmin')")
    @PostMapping(path = "/private/api/centres")
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

    @PreAuthorize("hasAuthority('ROLE_superadmin')")
    @PutMapping(path = "/private/api/centre/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Centre c){
        service.update(id, c);
    }

}
