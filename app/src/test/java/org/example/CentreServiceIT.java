package org.example;

import org.example.service.Centre;
import org.example.service.CentreService;
import org.example.repository.CentreRepository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CentreServiceIT {

    @Autowired
    CentreService centreService;

    @Autowired
    CentreRepository centreRepository;

    @Test
    public void ITCenterCreation() {

        var centre = new Centre(1, "Centre 1", "City 1", "1 rue jean jaures", "75000");

        this.centreRepository.save(centre);

        var result = centreService.readOne(1);

        Assertions.assertThat(result.isEqualTo(centre));
    }
}
