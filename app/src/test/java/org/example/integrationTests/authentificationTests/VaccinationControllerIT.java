package org.example.integrationTests.authentificationTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VaccinationControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "/private/api/vaccinations";

    @Test
    public void testReadAllVaccinationsAsAdmin() {
        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("admin", "admin");
        ResponseEntity<String> response = authRestTemplate.getForEntity(BASE_URL, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testReadAllVaccinationsAsDoctor() {
        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("doctor", "doctor");
        ResponseEntity<String> response = authRestTemplate.getForEntity(BASE_URL, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
   

    @Test
    public void testValidateVaccinationAsDoctorOfDifferentCenter() {
        int vaccinationId = 1;
        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("doctor2", "doctor2");
        ResponseEntity<String> response = authRestTemplate.postForEntity(BASE_URL + "/validation/" + vaccinationId, null, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void testReadVaccinationsForCentreAsAdminOfDifferentCentre() {
        int centreId = 1;
        LocalDate date = LocalDate.now();
        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("admin2", "admin2");
        ResponseEntity<String> response = authRestTemplate.getForEntity(BASE_URL + "/" + centreId + "/" + date, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
