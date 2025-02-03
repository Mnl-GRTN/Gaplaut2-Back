package org.example.service;

import org.example.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {

    @Autowired
    private VaccinationRepository vaccinationRepository;

    public void create(Vaccination p){
        vaccinationRepository.save(p); //Implemented by JpaRepository
    }

    public Iterable<Vaccination> readAll(){
        return vaccinationRepository.findAll();
    }

    public Vaccination readOne(int id){
        return vaccinationRepository.findById(id).get();
    }

}
