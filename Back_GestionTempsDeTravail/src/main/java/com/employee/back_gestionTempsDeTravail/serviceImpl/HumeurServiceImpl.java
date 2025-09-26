package com.employee.back_gestionTempsDeTravail.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.back_gestionTempsDeTravail.dto.HumeurDto;
import com.employee.back_gestionTempsDeTravail.repository.HumeurRepository;
import com.employee.back_gestionTempsDeTravail.service.HumeurService;
import com.employee.back_gestionTempsDeTravail.transformer.HumeurTransformer;

@Service
public class HumeurServiceImpl implements HumeurService {
    @Autowired
    private HumeurRepository humeurRepository;

    @Autowired
    private HumeurTransformer humeurTransformer;

    @Override
    public List<HumeurDto> afficherListHumeur() {
        return humeurTransformer.fromEntityListToDtoList(humeurRepository.findAll());
    }

    @Override
    public void ajouterHumeur(HumeurDto humeur) {
        humeurRepository.save(humeurTransformer.fromDtoToEntity(humeur));
    }
    
}
