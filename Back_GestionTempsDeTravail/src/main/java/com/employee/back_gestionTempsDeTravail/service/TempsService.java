package com.employee.back_gestionTempsDeTravail.service;

import java.sql.Date;
import java.util.List;

import com.employee.back_gestionTempsDeTravail.dto.TempsDto;


public interface TempsService {
    TempsDto afficherTemps(Long id);
    List<TempsDto> afficherListTemps();
    void ajouterTemps(TempsDto Temps);
    TempsDto findTempsByDate(Date date,Long idEmployee);
    TempsDto modifierTemps(TempsDto tempsDto) throws java.text.ParseException;
}
