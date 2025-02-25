package org.example.repository;

import java.util.Optional;

import org.example.service.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    //JpaRepository This automatically provides implementations for common operations like saving,
    //updating, finding by ID, and deleting entities.

    //Optional is a container object which may or may not contain a non-null value.
    Optional<Doctor> findByEmail(String email);
}
