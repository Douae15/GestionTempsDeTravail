package com.employee.back_gestionTempsDeTravail.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.back_gestionTempsDeTravail.dto.RapportDto;
import com.employee.back_gestionTempsDeTravail.repository.RapportRepository;
import com.employee.back_gestionTempsDeTravail.service.RapportService;
import com.employee.back_gestionTempsDeTravail.transformer.RapportTransformer;

@Service
public class RapportServiceImpl implements RapportService {
    @Autowired
    private RapportTransformer rapportTransformer;

    @Autowired
    private RapportRepository rapportRepository;

    @Override
    public List<RapportDto> getRapportsByEmployee(Long idEmployee) {
        return rapportTransformer.fromEntityListToDtoList(rapportRepository.findByIdEmployee(idEmployee));
    }
    
}
