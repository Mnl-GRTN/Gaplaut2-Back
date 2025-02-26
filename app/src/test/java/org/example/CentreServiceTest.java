package org.example;

import java.util.Optional;

import org.example.service.Centre;
import org.example.service.CentreService;
import org.example.repository.CentreRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

        var centre = new Centre();

        centre.setId(1);
        centre.setCentreName("Centre 1");
        centre.setCity("City 1");
        
        Mockito.when(centreRepository.findById(1)).thenReturn(Optional.of(centre));

        var result = centreService.readOne(1);

        Assertions.assertThat(result.isEqualTo(centre));    
    }
}