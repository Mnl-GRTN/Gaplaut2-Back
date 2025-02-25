package org.example.service;

import org.example.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

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

    @PostConstruct
    public void init(){

        if (centreRepository.count() == 0) {
            for (int i = 1; i <= 20; i++) {
                Centre centre = new Centre(i, "Centre" + i, "City1", "Address" + i, "12345");
                centreRepository.save(centre);
            }
        }
    }
}
