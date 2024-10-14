package org.example.rest;

import org.example.service.DoctorService;
import org.example.service.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;


@RestController
public class DoctorRestController {
    
    //The DoctorRestController is part of the Presentation Layer,
    //and it handles HTTP requests (GET, POST, PUT, DELETE).


    @Autowired
    private DoctorService service;

    @PostMapping(path = "/doctors")
    public void create(@RequestBody Doctor p) throws URISyntaxException{
        service.create(p);
    }


}
