package org.example.controller;

import org.example.service.Vaccination;
import org.example.service.VaccinationService;
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

    @PostMapping(path = "public/api/vaccinations")
    public void create(@RequestBody Vaccination p) throws URISyntaxException{
        service.create(p);
    }

    @GetMapping(path = "public/api/vaccinations")
    public Iterable<Vaccination> read(){
        return service.readAll();
    }

    @GetMapping(path = "/api/vaccination/{id}")
    public Vaccination readOne(@PathVariable("id") int id){
        return service.readOne(id);
    }


}
