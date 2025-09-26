package com.employee.back_gestionTempsDeTravail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.back_gestionTempsDeTravail.entity.Humeur;


@Repository
public interface HumeurRepository extends JpaRepository<Humeur,String> {
    
}
