package org.example;

import java.util.Optional;

import org.example.service.Centre;
import org.example.service.CentreService;
import org.example.repository.CentreRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CentreServiceIT {

    @Autowired
    CentreService centreService;

    @Autowired
    CentreRepository centreRepository;

    @Test
    public void ITCenterCreation() {

        var centre = new Centre();

        centre.setId(1);
        centre.setCentreName("Centre 1");
        centre.setCity("City 1");

        this.centreRepository.save(centre);

        var result = centreService.readOne(1);

        Assertions.assertThat(result.isEqualTo(centre));
        
    
    }
}
