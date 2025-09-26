package com.employee.back_gestionTempsDeTravail.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.back_gestionTempsDeTravail.dto.PausesDto;
import com.employee.back_gestionTempsDeTravail.entity.Pauses;
import com.employee.back_gestionTempsDeTravail.repository.TempsRepository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Component
public class PausesTransformer implements Transformer<PausesDto,Pauses> {

    

    @Autowired
    private TempsRepository tempsRepository;

    @Override
    public PausesDto fromEntityToDto(Pauses pauses){
        return PausesDto.builder()
                .debutPause(pauses.getDebutPause())
                .finPause(pauses.getFinPause())
                .idPause(pauses.getIdPause())
                .statut(pauses.getStatut())
                .idTemps(pauses.getTemps().getIdTemps())
                .build();
    }

    @Override
    public Pauses fromDtoToEntity(PausesDto pausesDto){
        return Pauses.builder()
                .debutPause(pausesDto.getDebutPause())
                .finPause(pausesDto.getFinPause())
                .idPause(pausesDto.getIdPause())
                .statut(pausesDto.getStatut())
                .temps(tempsRepository.findById(pausesDto.getIdTemps()).get())
                .build();
    }

    @Override
    public List<PausesDto> fromEntityListToDtoList(List<Pauses> pauses) {
        List<PausesDto> pausesDTOList = new ArrayList<>();

        for (Pauses pause : pauses) {
            pausesDTOList.add(this.fromEntityToDto(pause));
        }

        return pausesDTOList;
    }

    @Override
    public List<Pauses> fromDtoListToEntityList(List<PausesDto> pausesDto) {
        List<Pauses> pausesList = new ArrayList<>();

        for (PausesDto pause : pausesDto) {
            pausesList.add(this.fromDtoToEntity(pause));
        }

        return pausesList;
    }
    
}
