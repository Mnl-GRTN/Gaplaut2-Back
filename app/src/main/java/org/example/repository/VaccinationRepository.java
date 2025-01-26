package org.example.repository;

import org.example.service.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Integer> {

    //JpaRepository This automatically provides implementations for common operations like saving,
    //updating, finding by ID, and deleting entities.

}
