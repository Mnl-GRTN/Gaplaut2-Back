package org.example.service;

import org.example.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {

    //The service layer abstracts and encapsulates the business operations,
    //such as validations, complex operations, or transactions.

    //Acts between DoctorRestController and DoctorRepository

    @Autowired
    private VaccinationRepository vaccinationRepository;

    public void create(Doctor p){
        vaccinationRepository.save(p); //Implemented by JpaRepository
    }

    public Iterable<Doctor> readAll(){
        return vaccinationRepository.findAll();
    }

    public Doctor readOne(int id){
        return vaccinationRepository.findById(id).get();
    }

}
