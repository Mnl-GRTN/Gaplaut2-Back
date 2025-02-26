package org.example.controller;

import org.example.repository.DoctorRepository;
import org.example.service.Vaccination;
import org.example.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class VaccinationRestController {
    
    //The DoctorRestController is part of the Presentation Layer,
    //and it handles HTTP requests (GET, POST, PUT, DELETE).


    @Autowired
    private VaccinationService service;

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping(path = "/public/api/vaccinations")
    public void create(@RequestBody Vaccination p) throws URISyntaxException{
        service.create(p);
    }

    @GetMapping(path = "/private/api/vaccinations")
    public Iterable<Vaccination> read(){
        return service.readAll();
    }

    @GetMapping(path = "/private/api/vaccination/{id}")
    public Vaccination readOne(@PathVariable("id") int id){
        return service.readOne(id);
    }

    @PutMapping(path = "/private/api/vaccination/validation/{id}")
    public void validateVaccination(@PathVariable("id") int id){

        // Get the roles of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Check if the user is a doctor or an admin
        if (userRoles.contains("ROLE_doctor") || userRoles.contains("ROLE_admin")){
            
            // Check if the user is from the same centre as the vaccination
            Integer vaccinationCentreId = service.readOne(id).getCentre().getId();
            Integer userCentreId = doctorRepository.findByEmail(authentication.getName()).get().getCentre().getId();

            if (vaccinationCentreId == userCentreId){
                service.validateVaccination(id);
                return;
            }
        }

        throw new RuntimeException("Vous n'avez pas les droits pour effectuer cette action");
    }

    @GetMapping(path = "/private/api/vaccinations/{centreId}/{date}")
    public Iterable<Vaccination> readDateCentre(@PathVariable("centreId") int centreId, @PathVariable("date") LocalDate date){

        // Get the roles of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Check if the user is a doctor or an admin
        if (userRoles.contains("ROLE_doctor") || userRoles.contains("ROLE_admin")){
            
            // Check if the user is from the centre of the vaccination
            Integer userCentreId = doctorRepository.findByEmail(authentication.getName()).get().getCentre().getId();

            if (centreId == userCentreId){
                return service.readCentreDate(centreId, date);
            }
        }
        throw new RuntimeException("Vous n'avez pas les droits pour effectuer cette action");
    }




}
