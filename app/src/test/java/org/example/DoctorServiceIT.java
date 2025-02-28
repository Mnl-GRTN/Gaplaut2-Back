package org.example;

import org.example.repository.DoctorRepository;
import org.example.service.Centre;
import org.example.service.Doctor;
import org.example.service.DoctorService;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DoctorServiceIT {
    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorRepository doctorRepository;

    @Test
    public void ITDoctorCreation() {
        var centre = Mockito.mock(Centre.class);        
        var doctor = new Doctor(1, "Dupont", "Jean", centre, "jean.d@email.com", "password", null);

        this.doctorRepository.save(doctor);

        var result = doctorService.readOne(1);

        Assertions.assertThat(result.isEqualTo(doctor));
        
    
    }
}
