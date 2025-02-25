package org.example;

import java.util.Optional;

import org.example.service.Centre;
import org.example.service.Doctor;
import org.example.service.DoctorService;
import org.example.repository.DoctorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DoctorServiceTest {
    DoctorService doctorService;
    DoctorRepository doctorRepository;

    @BeforeEach
    void setUp() {
        doctorRepository = Mockito.mock(DoctorRepository.class);        
        doctorService = new DoctorService(doctorRepository);
    }

    @Test
    public void testDoctorCreation() {
        var centre = Mockito.mock(Centre.class);        
        var doctor = new Doctor(1, "Dupont", "Jean", centre, "jean.d@email.com", "password", null);

        Mockito.when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));

        var result = doctorService.readOne(1);

        Assertions.assertThat(result.isEqualTo(doctor));
    }
}