package com.employee.back_gestionTempsDeTravail.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.employee.back_gestionTempsDeTravail.dto.HumeurDto;
import com.employee.back_gestionTempsDeTravail.entity.Humeur;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Component
public class HumeurTransformer implements Transformer<HumeurDto,Humeur> {

    @Override
    public HumeurDto fromEntityToDto(Humeur humeur){
        return HumeurDto.builder()
                .token(humeur.getToken())
                .date(humeur.getDate())
                .humeur(humeur.getHumeur())
                .build();
    }

    @Override
    public Humeur fromDtoToEntity(HumeurDto humeurDto){
        return Humeur.builder()
                .token(humeurDto.getToken())
                .date(humeurDto.getDate())
                .humeur(humeurDto.getHumeur())
                .build();
    }

    @Override
    public List<HumeurDto> fromEntityListToDtoList(List<Humeur> humeurs) {
        List<HumeurDto> humeurDTOList = new ArrayList<>();

        for (Humeur humeur : humeurs) {
            humeurDTOList.add(this.fromEntityToDto(humeur));
        }

        return humeurDTOList;
    }

    @Override
    public List<Humeur> fromDtoListToEntityList(List<HumeurDto> humeurDtos) {
        List<Humeur> humeursList = new ArrayList<>();

        for (HumeurDto humeurDto : humeurDtos) {
            humeursList.add(this.fromDtoToEntity(humeurDto));
        }

        return humeursList;
    }
    
}
