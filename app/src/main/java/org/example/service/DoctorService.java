package org.example.service;

import org.example.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    //The service layer abstracts and encapsulates the business operations,
    //such as validations, complex operations, or transactions.

    //Acts between DoctorRestController and DoctorRepository

    @Autowired
    private DoctorRepository doctorRepository;

    public void create(Doctor p){
        doctorRepository.save(p); //Implemented by JpaRepository
    }

    public Iterable<Doctor> readAll(){
        return doctorRepository.findAll();
    }

    public Doctor readOne(int id){
        return doctorRepository.findById(id).get();
    }

}
