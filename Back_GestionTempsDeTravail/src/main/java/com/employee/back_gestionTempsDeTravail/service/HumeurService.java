package com.employee.back_gestionTempsDeTravail.service;

import java.util.List;

import com.employee.back_gestionTempsDeTravail.dto.HumeurDto;

public interface HumeurService {
    List<HumeurDto> afficherListHumeur();
    void ajouterHumeur(HumeurDto humeur);
}
