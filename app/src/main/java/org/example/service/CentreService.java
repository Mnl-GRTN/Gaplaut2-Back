package org.example.service;

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
        return centreRepository.findAll();
    }

    public Centre readOne(int id){
        return centreRepository.findById(id).get();
    }
}
