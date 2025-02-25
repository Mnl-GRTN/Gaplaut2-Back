package org.example.service;

import java.util.Collections;

import org.example.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class DoctorService {

    //The service layer abstracts and encapsulates the business operations,
    //such as validations, complex operations, or transactions.

    //Acts between DoctorRestController and DoctorRepository

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(Doctor p){
        // Encode the password before saving
        p.setPassword(passwordEncoder.encode(p.getPassword()));
        doctorRepository.save(p);
    }

    public Iterable<Doctor> readAll(){
        return doctorRepository.findAll();
    }

    public Doctor readOne(int id){
        return doctorRepository.findById(id).get();
    }

    public void update(int id, Doctor d){
        // Check if the doctor exists
        Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Update the doctor details with the new details
        existingDoctor.setCentre(d.getCentre());
        existingDoctor.setFirstName(d.getFirstName());
        existingDoctor.setLastName(d.getLastName());
        existingDoctor.setRoles(d.getRoles());
        existingDoctor.setEmail(d.getEmail());
        existingDoctor.setPassword(passwordEncoder.encode(d.getPassword()));

        doctorRepository.save(existingDoctor);
    }

    @PostConstruct
    public void init(){

        Role role = new Role(1,"superadmin");
        Centre centre = new Centre(1, "Centre" + 1, "City1", "Address" + 1, "12345");

        if (doctorRepository.count() == 0) {
            Doctor superadmin = new Doctor(1,  "superadmin" ,"superadmin", 
                                            centre, "superadmin", "superadmin",
                                            Collections.singleton(role));
    
            superadmin.setPassword(passwordEncoder.encode(superadmin.getPassword()));
            doctorRepository.save(superadmin);
            
        }
    }

}
