package com.employee.back_gestionTempsDeTravail.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.back_gestionTempsDeTravail.dto.RapportDto;
import com.employee.back_gestionTempsDeTravail.entity.Rapport;
import com.employee.back_gestionTempsDeTravail.repository.RapportRepository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Component
public class RapportTransformer implements Transformer<RapportDto,Rapport> {

    @Autowired
    private EmployeeTransformer employeeTransformer;

    @Autowired
    private RapportRepository RapportRepository;

    @Override
    public RapportDto fromEntityToDto(Rapport rapport){
        return RapportDto.builder()
                .idRapport(rapport.getIdRapport())
                .date(rapport.getDate())
                .path(rapport.getPath())
                .Rappemployee(employeeTransformer.fromEntityToDto(rapport.getRappemployee()))
                .build();
    }
    
    @Override
    public Rapport fromDtoToEntity(RapportDto rapportDto){
        return Rapport.builder()
                .idRapport(rapportDto.getIdRapport())
                .date(rapportDto.getDate())
                .path(rapportDto.getPath())
                .Rappemployee(employeeTransformer.fromDtoToEntity(rapportDto.getRappemployee()))
                .build();
    }

    @Override
    public List<RapportDto> fromEntityListToDtoList(List<Rapport> rapports) {
        List<RapportDto> rapportsDTOList = new ArrayList<>();

        for (Rapport rapport : rapports) {
            rapportsDTOList.add(this.fromEntityToDto(rapport));
        }

        return rapportsDTOList;
    }

    @Override
    public List<Rapport> fromDtoListToEntityList(List<RapportDto> rapportsDto) {
        List<Rapport> rapportsList = new ArrayList<>();

        for (RapportDto rapport : rapportsDto) {
            rapportsList.add(this.fromDtoToEntity(rapport));
        }

        return rapportsList;
    }
    
    
}

