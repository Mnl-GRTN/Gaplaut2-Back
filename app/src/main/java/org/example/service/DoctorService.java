package org.example.service;

import java.util.ArrayList;
import java.util.List;

import org.example.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public void create(Doctor p){
        doctorRepository.save(p);
    }

}
