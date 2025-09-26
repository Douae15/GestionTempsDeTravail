package com.employee.back_gestionTempsDeTravail.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.back_gestionTempsDeTravail.entity.Pauses;

@Repository
public interface PausesRepository extends JpaRepository<Pauses,Long> {
    @Query(value = "SELECT temps.*, pauses.id_pause , pauses.debut_pause ,pauses.fin_pause, pauses.statut FROM pauses  Join temps  on pauses.id_temps=temps.id_temps WHERE temps.id_employee = ? and temps.date=? ORDER BY pauses.id_pause DESC LIMIT 1;", nativeQuery = true)
    Pauses findLastPause(@Param("id_employee") Long id_employee,@Param("date") Date date);
    
}
