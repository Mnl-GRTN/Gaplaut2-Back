package org.example.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.example.repository.CentreRepository;
import org.springframework.stereotype.Service;

@Service
public class CentreService {


    private CentreRepository centreRepository;
 
    public CentreService(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
    }

    public void create(Centre c){
        centreRepository.save(c);
    }

    public Iterable<Centre> readAll(){
        List<Centre> centres = centreRepository.findAll();

        // Remove the first centre (id = 1) from the list because its the superadmin's centre
        centres = StreamSupport.stream(centres.spliterator(), false)
        .filter(obj -> obj.getId() != 1)
        .collect(Collectors.toList());

        return centres;
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
