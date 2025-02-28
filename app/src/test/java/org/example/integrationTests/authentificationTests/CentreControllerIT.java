package org.example.integrationTests.authentificationTests;

import org.example.service.Centre;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CentreControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String URL = "/private/api/centres";

    @Test
    public void testCreateCentreAvecSuperadmin() {
        Centre centre = new Centre(9595, "El Grando Centrissimo Cinque", "City 5", "5 rue de la 5ème ville", "55555");
        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("superadmin", "superadmin");

        ResponseEntity<String> response = authRestTemplate.postForEntity(URL, centre, String.class);

        Assertions.assertThat(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void testAccesRefusePourRandom() {
        Centre centre = new Centre(909090, "Centre Interdit", "City X", "Adresse X", "99999");

        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("randomUser", "randomPasswordOfTheUser");

        ResponseEntity<String> response = authRestTemplate.postForEntity(URL, centre, String.class);

        Assertions.assertThat(response.getStatusCode() == HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testAccesSansAuthentification() {
        Centre centre = new Centre(111211, "Centre Public", "City Y", "Adresse Y", "11111");

        ResponseEntity<String> response = restTemplate.postForEntity(URL, centre, String.class);

        Assertions.assertThat(response.getStatusCode() == HttpStatus.UNAUTHORIZED);
    }
}
