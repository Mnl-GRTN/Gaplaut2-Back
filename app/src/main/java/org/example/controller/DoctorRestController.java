package org.example.controller;

import org.example.service.DoctorService;
import org.example.service.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.example.service.Role;



@RestController
public class DoctorRestController {
    
    //The DoctorRestController is part of the Presentation Layer,
    //and it handles HTTP requests (GET, POST, PUT, DELETE).


    @Autowired
    private DoctorService service;

    @PostMapping(path = "/private/api/doctors")
    public void create(@RequestBody Doctor d) throws URISyntaxException{

        // Get the roles of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Get the roles of the doctor to be created
        List<String> creationRoles = d.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());


        if (creationRoles.contains("superadmin") && userRoles.contains("ROLE_superadmin")){
            service.create(d);
        }


        else{
            throw new RuntimeException("Vous n'avez pas les droits pour effectuer cette action");
        }
 
        
    }

    @GetMapping(path = "/private/api/doctors")
    public Iterable<Doctor> read(){
        return service.readAll();
    }

    @GetMapping(path = "/private/api/doctor/{id}")
    public Doctor readOne(@PathVariable("id") int id){
        return service.readOne(id);
    }

    @PutMapping(path = "/private/api/doctor/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Doctor d){
        // Get the roles of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Get the roles of the doctor to be updated
        List<String> updateRoles = d.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());


        if (updateRoles.contains("superadmin") && userRoles.contains("ROLE_superadmin")){
            service.update(id, d);
        }
        else{
            throw new RuntimeException("Vous n'avez pas les droits pour effectuer cette action");
        }
    }


}
