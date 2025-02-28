package org.example.service;

import java.time.LocalDate;

import org.example.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {

    @Autowired
    private VaccinationRepository vaccinationRepository;

    public VaccinationService(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }

    public void create(Vaccination p){
        vaccinationRepository.save(p); //Implemented by JpaRepository
    }

    public Iterable<Vaccination> readAll(){
        return vaccinationRepository.findAll();
    }

    public Vaccination readOne(int id){
        return vaccinationRepository.findById(id).get();
    }

    public void validateVaccination(int id){
        if(!vaccinationRepository.existsById(id)){
            throw new IllegalArgumentException("Vaccination with id " + id + " does not exist.");
        }

        Vaccination vaccination = vaccinationRepository.findById(id).get();
        vaccination.setIsVaccined(true);
        vaccinationRepository.save(vaccination);
    }

    public Iterable<Vaccination> readCentreDate(int centreId, LocalDate date){
        
        return vaccinationRepository.findByCentre_IdAndDate(centreId, date);
    }

}
