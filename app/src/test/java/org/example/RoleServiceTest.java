package org.example;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.example.repository.DoctorRepository;
import org.example.repository.RoleRepository;
import org.example.service.Centre;
import org.example.service.Doctor;
import org.example.service.DoctorService;
import org.example.service.Role;
import org.example.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RoleServiceTest {
    RoleService roleService;
    RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        roleRepository = Mockito.mock(RoleRepository.class);        
        roleService = new RoleService(roleRepository);
    }

    @Test
    public void testRoleCreation() {
        var role = new Role(1, "ROLE_ADMIN");    
        Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(role));
        
        var result = roleService.readOne(1);

        Assertions.assertThat(result.isEqualTo(role));
    }
}
