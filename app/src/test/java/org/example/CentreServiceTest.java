package org.example;

import org.example.service.Centre;
import org.example.service.CentreService;
import org.example.repository.CentreRepository;

import java.util.Optional;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CentreServiceTest {

    CentreService centreService;
    
    CentreRepository centreRepository;
    
    @BeforeEach
    void setUp() {
        centreRepository = Mockito.mock(CentreRepository.class);
        centreService = new CentreService(centreRepository);
    }
    
    @Test
    public void testCenterCreation() {

        var centre = new Centre(1, "Centre 1", "City 1", "1 rue jean jaures", "75000");

        Mockito.when(centreRepository.findById(1)).thenReturn(Optional.of(centre));

        var result = centreService.readOne(1);

        Assertions.assertThat(result.isEqualTo(centre));    
    }
}