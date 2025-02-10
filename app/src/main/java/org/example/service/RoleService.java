package org.example.service;

import org.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class RoleService {

    //The service layer abstracts and encapsulates the business operations,
    //such as validations, complex operations, or transactions.

    //Acts between DoctorRestController and DoctorRepository

    @Autowired
    private RoleRepository roleRepository;

    public void create(Role role){
        roleRepository.save(role); //Implemented by JpaRepository
    }

    public Iterable<Role> readAll(){
        return roleRepository.findAll();
    }

    public Role readOne(int id){
        return roleRepository.findById(id).get();
    }

    @PostConstruct
    public void init(){

        roleRepository.deleteAll();

        Role role = new Role(1,"superadmin");
        Role role2 = new Role(2, "admin");
        Role role3 = new Role(3,"médecin");

        roleRepository.save(role);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }

}
