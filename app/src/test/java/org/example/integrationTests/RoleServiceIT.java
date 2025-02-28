package org.example.integrationTests;

import org.assertj.core.api.Assertions;

import org.example.repository.RoleRepository;
import org.example.service.Role;
import org.example.service.RoleService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleServiceIT {
    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void ITRoleCreation() {
        var role = new Role(1, "superadmin");    
        this.roleRepository.save(role);    

        var result = roleService.readOne(1);

        Assertions.assertThat(result.isEqualTo(role));
    }
}
