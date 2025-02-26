package org.example.repository;

import java.time.LocalDate;

import org.example.service.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Integer> {

    //JpaRepository This automatically provides implementations for common operations like saving,
    //updating, finding by ID, and deleting entities.

    //Optional is a container object which may or may not contain a non-null value.
    Iterable<Vaccination> findByCentre_IdAndDate(Integer centreId, LocalDate date);

    Iterable<Vaccination> findByCentre_IdAndDateAndLastName(Integer centreId, LocalDate date, String lastName);

}
