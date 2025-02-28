package org.example.integrationTests.authentificationTests;

import org.example.service.Centre;
import org.example.service.Doctor;
import org.example.service.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String URL = "/private/api/doctors";

    @Test
    public void testCreateDoctorAsSuperadmin() {
        Role role3 = new Role(3, "doctor");
        Centre centre2 = new Centre(2, "CHU NANCY", "NANCY", "1 rue de la santé", "54000");
        Doctor doctor = new Doctor(1, "doctor", "doctor",centre2, "doctoro", "doctor",Collections.singleton(role3));

        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("superadmin", "superadmin");

        ResponseEntity<String> response = authRestTemplate.postForEntity(URL, doctor, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCreateDoctorAsAdminOfSameCenter() {
        Role role3 = new Role(3, "doctor");
        Centre centre2 = new Centre(2, "CHU NANCY", "NANCY", "1 rue de la santé", "54000");
        Doctor doctor = new Doctor(1, "doctor", "doctor",centre2, "doctoroo", "doctor",Collections.singleton(role3));
        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("admin", "admin");

        ResponseEntity<String> response = authRestTemplate.postForEntity(URL, doctor, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCreateDoctorAsAdminOfDifferentCenter() {
        Role role3 = new Role(3, "doctor");
        Centre centre2 = new Centre(2, "CHU NANCY", "NANCY", "1 rue de la santé", "54000");
        Doctor doctor = new Doctor(1, "doctor", "doctor",centre2, "doctor", "doctor",Collections.singleton(role3));
        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("admin2", "admin2");

        ResponseEntity<String> response = authRestTemplate.postForEntity(URL, doctor, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void testCreateDoctorAsFakeUser() {
        Role role3 = new Role(3, "doctor");
        Centre centre2 = new Centre(2, "CHU NANCY", "NANCY", "1 rue de la santé", "54000");
        Doctor doctor = new Doctor(1, "doctor", "doctor",centre2, "doctor", "doctor",Collections.singleton(role3));
        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("fakeuser", "fakeuserpassword");

        ResponseEntity<String> response = authRestTemplate.postForEntity(URL, doctor, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testCreateDoctorAsSomeoneUnregistered() {
        Role role3 = new Role(3, "doctor");
        Centre centre2 = new Centre(2, "CHU NANCY", "NANCY", "1 rue de la santé", "54000");
        Doctor doctor = new Doctor(1, "doctor", "doctor",centre2, "doctor", "doctor",Collections.singleton(role3));

        ResponseEntity<String> response = restTemplate.postForEntity(URL, doctor, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
