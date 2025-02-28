package org.example.controller;

import org.example.service.DoctorService;
import org.example.repository.DoctorRepository;
import org.example.service.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.example.service.Role;



@RestController
public class DoctorRestController {
    
    //The DoctorRestController is part of the Presentation Layer,
    //and it handles HTTP requests (GET, POST, PUT, DELETE).


    @Autowired
    private DoctorService service;

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping(path = "/private/api/doctors")
    public void create(@RequestBody Doctor d) throws URISyntaxException{

        // Get the roles of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Get the roles of the doctor to be created
        List<String> creationRoles = d.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());

        // Check if the user has the superadmin role
        if (userRoles.contains("ROLE_superadmin")){
            service.create(d);
            return;
        }

        // Check if the user has the admin role, and the doctor to be updated is not an admin or superadmin
        else if (userRoles.contains("ROLE_admin") 
                    && !creationRoles.contains("ROLE_admin") 
                    && !creationRoles.contains("ROLE_superadmin")){
            
            // Check if the admin and doctor belong to the same centre
            Integer adminCentre = doctorRepository.findByEmail(authentication.getName()).get().getCentre().getId();
            Integer doctorCentre = d.getCentre().getId();
            if (adminCentre == doctorCentre){
                service.create(d);
                return;
            }
        }

        throw new RuntimeException("Vous n'avez pas les droits pour effectuer cette action");

    }

    @GetMapping(path = "/private/api/doctors")
    public Iterable<Doctor> read(){
        return service.readAll();
    }

    @GetMapping(path = "/private/api/doctor/{id}")
    public Doctor readOne(@PathVariable("id") int id){
        return service.readOne(id);
    }

    @PutMapping(path = "/private/api/doctor/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Doctor d){
        // Get the roles of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Get the roles of the doctor to be updated
        List<String> updateRoles = d.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());

        // Check if the user has the superadmin role
        if (userRoles.contains("ROLE_superadmin")){
            service.update(id, d);
            return;
        }

        // Check if the user has the admin role, and the doctor to be updated is not an admin or superadmin
        else if (userRoles.contains("ROLE_admin") 
                    && !updateRoles.contains("ROLE_admin") 
                    && !updateRoles.contains("ROLE_superadmin")){
            
            // Check if the admin and doctor belong to the same centre
            Integer adminCentre = doctorRepository.findByEmail(authentication.getName()).get().getCentre().getId();
            Integer doctorCentre = doctorRepository.findById(id).get().getCentre().getId();
            if (adminCentre == doctorCentre){
                service.update(id,d);
                return;
            }
        }

        throw new RuntimeException("Vous n'avez pas les droits pour effectuer cette action");
        
    }

    @DeleteMapping(path = "/private/api/doctor/{id}")
    public void delete(@PathVariable("id") int id){
        // Get the roles of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Get the roles of the doctor to be deleted
        List<String> deleteRoles = service.readOne(id).getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());

        // Check if the user has the superadmin role
        if(userRoles.contains("ROLE_superadmin")){
            service.delete(id);
            return;
        }

        // Check if the user has the admin role, and the doctor to be updated is not an admin or superadmin
        else if (userRoles.contains("ROLE_admin") 
                    && !deleteRoles.contains("ROLE_admin") 
                    && !deleteRoles.contains("ROLE_superadmin")){
            
            // Check if the admin and doctor belong to the same centre
            Integer adminCentre = doctorRepository.findByEmail(authentication.getName()).get().getCentre().getId();
            Integer doctorCentre = service.readOne(id).getCentre().getId();
            if (adminCentre == doctorCentre){
                service.delete(id);
                return;
            }
        }   
        throw new RuntimeException("Vous n'avez pas les droits pour effectuer cette action");
    }

    @GetMapping(path = "/private/api/doctors/centre/{centreId}")
    public Iterable<Doctor> readCentre(@PathVariable("centreId") int centreId){

        // Get the roles of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        Iterable<Doctor> doctors = service.readByCentre_Id(centreId);

        if (userRoles.contains("ROLE_superadmin")){
            return doctors;
        }

        Integer adminCentre = doctorRepository.findByEmail(authentication.getName()).get().getCentre().getId();

        // Check if the user has the admin role, and the doctor to be fetched are in the same center as the admin
        if (userRoles.contains("ROLE_admin") && adminCentre == centreId){
            //remove admin doctors from the list
            doctors = ((Collection<Doctor>) doctors).stream().filter(d -> !d.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()).contains("admin")).collect(Collectors.toList());
            return doctors;
        }

        throw new RuntimeException("Vous n'avez pas les droits pour effectuer cette action");
    }
}
