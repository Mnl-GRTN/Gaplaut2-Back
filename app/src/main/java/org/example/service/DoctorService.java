package org.example.service;

import org.example.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void delete(int id){
        doctorRepository.deleteById(id);
    }
}
