package com.employee.back_gestionTempsDeTravail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.back_gestionTempsDeTravail.entity.Rapport;

@Repository
public interface RapportRepository extends JpaRepository<Rapport,Long>  {
    @Query(value = "SELECT * FROM `rapport` WHERE id_employee=?", nativeQuery = true)
    List<Rapport> findByIdEmployee(@Param("id_employee") Long idEmployee);
    
}
