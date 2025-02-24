package org.example.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

    @GetMapping("private/api/auth/user-info")
    public Map<String, Object> userInfo() {
        Map<String, Object> userInfo = new HashMap<>();
        //Basic Auth Spring Security Roles and User Info
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        userInfo.put("username", username);
        userInfo.put("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return userInfo;
    }
    
}

