package org.example.repository;

import org.example.service.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Integer>{
    
    
}
