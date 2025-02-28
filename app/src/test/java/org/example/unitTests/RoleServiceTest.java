package org.example.unitTests;

import org.example.repository.RoleRepository;
import org.example.service.Role;
import org.example.service.RoleService;

import java.util.Optional;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
        var role = new Role(1, "superadmin");    
        Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(role));
        
        var result = roleService.readOne(1);

        Assertions.assertThat(result.isEqualTo(role));
    }
}
