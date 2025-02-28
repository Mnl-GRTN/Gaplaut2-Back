package org.example.controller;

import org.example.service.Role;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class RoleRestController {
    
    @Autowired
    private RoleService service;

    @PostMapping(path = "/private/api/roles")
    public void create(@RequestBody Role r) throws URISyntaxException{
        service.create(r);
    }

    @GetMapping(path = "/private/api/roles")
    public Iterable<Role> read(){
        return service.readAll();
    }

    @GetMapping(path = "/private/api/role/{id}")
    public Role readOne(@PathVariable("id") int id){
        return service.readOne(id);
    }

}
