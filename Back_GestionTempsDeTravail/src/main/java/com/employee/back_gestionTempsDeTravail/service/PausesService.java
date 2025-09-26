package com.employee.back_gestionTempsDeTravail.service;

import java.util.List;

import com.employee.back_gestionTempsDeTravail.dto.PausesDto;

public interface PausesService {
    PausesDto afficherPause(Long id);
    List<PausesDto> afficherListPauses();
    PausesDto modifierPauses(Long idEmployee);
}
