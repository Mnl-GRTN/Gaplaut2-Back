package org.example;

import org.assertj.core.api.Assertions;
import org.example.repository.DoctorRepository;
import org.example.service.Centre;
import org.example.service.Doctor;
import org.example.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

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
