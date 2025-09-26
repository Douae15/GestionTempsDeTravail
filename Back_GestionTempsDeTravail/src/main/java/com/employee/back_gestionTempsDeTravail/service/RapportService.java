package com.employee.back_gestionTempsDeTravail.service;

import java.util.List;

import com.employee.back_gestionTempsDeTravail.dto.RapportDto;

public interface RapportService {
    
    List<RapportDto> getRapportsByEmployee(Long idEmployee);
}
