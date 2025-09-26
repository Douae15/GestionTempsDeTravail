package com.employee.back_gestionTempsDeTravail.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.back_gestionTempsDeTravail.entity.Temps;

@Repository
public interface TempsRepository extends JpaRepository<Temps,Long> {
    @Query(value = "SELECT * FROM temps WHERE date=? and id_employee=?", nativeQuery = true)
    Temps findByDate(@Param("date") Date date, @Param("id_employee") Long idEmployee);
    
}
