package org.example;

import org.assertj.core.api.Assertions;
import org.example.repository.RoleRepository;
import org.example.service.Role;
import org.example.service.RoleService;
import org.junit.jupiter.api.Test;

public class RoleServiceIT {
    RoleService roleService;
    RoleRepository roleRepository;

    @Test
    public void ITRoleCreation() {
        var role = new Role(1, "ROLE_ADMIN");    
        this.roleRepository.save(role);    

        var result = roleService.readOne(1);

        Assertions.assertThat(result.isEqualTo(role));
    }
}
