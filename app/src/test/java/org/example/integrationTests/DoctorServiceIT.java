package org.example.integrationTests;

import org.example.repository.DoctorRepository;
import org.example.service.Centre;
import org.example.service.Doctor;
import org.example.service.DoctorService;
import org.example.service.Role;

import java.util.Collections;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;

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
        Centre centre1 = new Centre(1, "Centre Administration", "PARIS", "4 rue de la santé", "75000");
        var role1 = new Role(1, "superadmin");
        Doctor doctor = new Doctor(1, "superadmin", "superadmin",centre1, "superadmin", "superadmin",
                Collections.singleton(role1));

        this.doctorRepository.save(doctor);

        var result = doctorService.readOne(1);

        Assertions.assertThat(result.isEqualTo(doctor));
        
    
    }
}
