package org.example.repository;

import org.example.service.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    //JpaRepository This automatically provides implementations for common operations like saving,
    //updating, finding by ID, and deleting entities.

}
