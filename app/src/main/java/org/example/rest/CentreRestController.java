package org.example.rest;

import org.example.service.Centre;
import org.example.service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class CentreRestController {
    
    @Autowired
    private CentreService service;

    @PostMapping(path = "/centres")
    public void create(@RequestBody Centre c) throws URISyntaxException{
        service.create(c);
    }

}
