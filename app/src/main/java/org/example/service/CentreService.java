package org.example.service;

import org.example.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentreService {

    @Autowired
    private CentreRepository centreRepository;
    
    public void create(Centre c){
        centreRepository.save(c);
    }

    public Iterable<Centre> readAll(){
        return centreRepository.findAll();
    }

    public Centre readOne(int id){
        return centreRepository.findById(id).get();
    }

    public void update(int id, Centre c){
        // Check if the centre exists
        Centre existingCentre = centreRepository.findById(id).orElseThrow(() -> new RuntimeException("Centre not found"));

        // Update the centre details with the new details
        existingCentre.setCentreName(c.getCentreName());
        existingCentre.setCity(c.getCity());
        existingCentre.setAddress(c.getAddress());
        existingCentre.setPostalCode(c.getPostalCode());

        centreRepository.save(existingCentre);
    }
}
