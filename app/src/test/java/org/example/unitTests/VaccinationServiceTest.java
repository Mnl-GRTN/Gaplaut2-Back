package org.example.unitTests;

import org.example.repository.VaccinationRepository;
import org.example.service.Centre;
import org.example.service.Vaccination;
import org.example.service.VaccinationService;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VaccinationServiceTest {
    
    VaccinationService vaccinationService;
    VaccinationRepository vaccinationRepository;

    @BeforeEach
    void setUp() {
        vaccinationRepository = Mockito.mock(VaccinationRepository.class);
        vaccinationService = new VaccinationService(vaccinationRepository);
    }

    @Test
    public void testVaccinationCreation() {

        var centre = Mockito.mock(Centre.class);        

        var vaccination = new Vaccination();
        vaccination.setId(1);
        vaccination.setCentre(centre);
        vaccination.setDate(LocalDate.of(2021, 12, 31));
        vaccination.setFirst_name("Martin");
        vaccination.setLast_name("Jean");
        vaccination.setIsVaccined(false);
        vaccination.setPhoneNumber("0612345678");
        vaccination.setMail("Jean.Martin@email.net");

        Mockito.when(vaccinationRepository.findById(1)).thenReturn(Optional.of(vaccination));

        var result = vaccinationService.readOne(1);

        Assertions.assertThat(result.isEqualTo(vaccination));
    }
}
