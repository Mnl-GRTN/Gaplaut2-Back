package org.example.service;

import org.example.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DoctorService {

    //The service layer abstracts and encapsulates the business operations,
    //such as validations, complex operations, or transactions.

    //Acts between DoctorRestController and DoctorRepository

    @Autowired
    private DoctorRepository doctorRepository;


    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
    
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

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

    public void update(int id, Doctor updatedDoctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        logger.info("Password changed: {}", updatedDoctor.isPasswordChanged());

        // Update fields
        existingDoctor.setFirstName(updatedDoctor.getFirstName());
        existingDoctor.setLastName(updatedDoctor.getLastName());
        existingDoctor.setEmail(updatedDoctor.getEmail());
        existingDoctor.setCentre(updatedDoctor.getCentre());
        existingDoctor.setRoles(updatedDoctor.getRoles());

        // Only hash the password if it was changed
        if (updatedDoctor.isPasswordChanged()) {
            existingDoctor.setPassword(passwordEncoder.encode(updatedDoctor.getPassword()));
        }

        doctorRepository.save(existingDoctor);
    }
    
    public void delete(int id){
        doctorRepository.deleteById(id);
    }

    public Iterable<Doctor> readByCentre_Id(int centreId){
        return doctorRepository.findByCentre_Id(centreId);
    }
}
