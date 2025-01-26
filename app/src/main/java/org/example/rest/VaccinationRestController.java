package org.example.rest;

import org.example.service.VaccinationService;
import org.example.service.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;


@RestController
public class VaccinationRestController {
    
    //The DoctorRestController is part of the Presentation Layer,
    //and it handles HTTP requests (GET, POST, PUT, DELETE).


    @Autowired
    private VaccinationService service;

    @PostMapping(path = "/api/vaccination")
    public void create(@RequestBody Doctor p) throws URISyntaxException{
        service.create(p);
    }

    @GetMapping(path = "/api/vaccinations")
    public Iterable<Doctor> read(){
        return service.readAll();
    }

    @GetMapping(path = "/api/vaccination/{id}")
    public Doctor readOne(@PathVariable("id") int id){
        return service.readOne(id);
    }


}
