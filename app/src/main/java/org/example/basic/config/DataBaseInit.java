package org.example.basic.config;

import java.time.LocalDate;
import java.util.Collections;

import org.example.repository.CentreRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.RoleRepository;
import org.example.repository.VaccinationRepository;
import org.example.service.Centre;
import org.example.service.Doctor;
import org.example.service.Role;
import org.example.service.Vaccination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataBaseInit {

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VaccinationRepository vaccinationRepository;

    @PostConstruct
    public void init() {
        Centre centre1 = new Centre(1, "Centre Administration", "PARIS", "4 rue de la santé", "75000");
        Centre centre2 = new Centre(2, "CHU NANCY", "NANCY", "1 rue de la santé", "54000");
        Centre centre3 = new Centre(3, "CHU STRASBOURG", "STRASBOURG", "2 rue de la santé", "67000");

        if (centreRepository.count() == 0) {
            Centre centre4 = new Centre(4, "CHU PARIS", "PARIS", "4 rue de la santé", "75000");
            Centre centre5 = new Centre(5, "CHU LYON", "LYON", "5 rue de la santé", "69000");
            Centre centre6 = new Centre(6, "CHU MARSEILLE", "MARSEILLE", "6 rue de la santé", "13000");
            Centre centre7 = new Centre(7, "CHU LILLE", "LILLE", "7 rue de la santé", "59000");
            Centre centre8 = new Centre(8, "CHU BORDEAUX", "BORDEAUX", "8 rue de la santé", "33000");

            centreRepository.save(centre1);
            centreRepository.save(centre2);
            centreRepository.save(centre3);
            centreRepository.save(centre4);
            centreRepository.save(centre5);
            centreRepository.save(centre6);
            centreRepository.save(centre7);
            centreRepository.save(centre8);
        }

        Role role1 = new Role(1, "superadmin");
        Role role2 = new Role(2, "admin");
        Role role3 = new Role(3, "doctor");

        if (roleRepository.count() == 0) {
            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);
        }

        

        if (doctorRepository.count() == 0) {

            // Create superadmin
            Doctor superadmin = new Doctor(1, "superadmin", "superadmin",centre1, "superadmin", "superadmin",
                Collections.singleton(role1));
            superadmin.setPassword(passwordEncoder.encode(superadmin.getPassword()));
            doctorRepository.save(superadmin);

            // Create admin for the first two centres
            Doctor admin = new Doctor(2, "admin", "admin", centre2, "admin", "admin", Collections.singleton(role2));
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            doctorRepository.save(admin);

            Doctor admin2 = new Doctor(3, "admin2", "admin2", centre3, "admin2", "admin2",
                Collections.singleton(role2));
            admin2.setPassword(passwordEncoder.encode(admin2.getPassword()));
            doctorRepository.save(admin2);

            // Create doctors for the first two centres
            Doctor doctor = new Doctor(4, "doctor", "doctor", centre2, "doctor", "doctor",
                Collections.singleton(role3));
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
            doctorRepository.save(doctor);

            Doctor doctor2 = new Doctor(5, "doctor2", "doctor2", centre3, "doctor2", "doctor2",
                Collections.singleton(role3));
            doctor2.setPassword(passwordEncoder.encode(doctor2.getPassword()));
            doctorRepository.save(doctor2);
        }

        if(vaccinationRepository.count() == 0) {
            LocalDate date = LocalDate.of(2021, 12, 12);
            LocalDate date2 = LocalDate.of(2021, 12, 13);
            Vaccination vaccination = new Vaccination(centre2, "john.doe@gmail.com", "0606060606", "Doe", "John", date);
            Vaccination vaccination2 = new Vaccination(centre2, "jane.doe@gmail.com", "0707070707", "Doe", "Jane", date);
            Vaccination vaccination3 = new Vaccination(centre3, "jean.dupont@gmail.com", "0808080808", "Dupont", "Jean", date);
            Vaccination vaccination4 = new Vaccination(centre3, "pierre.paul@gmail.com", "0909090909", "Paul", "Pierre", date2);

            vaccinationRepository.save(vaccination);
            vaccinationRepository.save(vaccination2);
            vaccinationRepository.save(vaccination3);
            vaccinationRepository.save(vaccination4);
        }
    }
}
