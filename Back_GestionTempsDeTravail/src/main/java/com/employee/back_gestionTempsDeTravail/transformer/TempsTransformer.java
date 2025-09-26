package com.employee.back_gestionTempsDeTravail.transformer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.back_gestionTempsDeTravail.dto.TempsDto;
import com.employee.back_gestionTempsDeTravail.entity.Temps;
import com.employee.back_gestionTempsDeTravail.repository.EmployeeRepository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Component
public class TempsTransformer implements Transformer<TempsDto,Temps> {

    @Autowired
    private PausesTransformer pausesTransformer;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public TempsDto fromEntityToDto(Temps temps){
        return TempsDto.builder()
                .idTemps(temps.getIdTemps())
                .date(temps.getDate())
                .heureDebut(temps.getHeureDebut())
                .heureFin(temps.getHeureFin())
                .idEmployee(temps.getEmployee().getIdEmployee())
                .pausesDto(pausesTransformer.fromEntityListToDtoList(temps.getPauses()))
                .build();
    }
    
    @Override
    public Temps fromDtoToEntity(TempsDto tempsDto){
        return Temps.builder()
                .idTemps(tempsDto.getIdTemps())
                .date(tempsDto.getDate())
                .heureDebut(tempsDto.getHeureDebut())
                .heureFin(tempsDto.getHeureFin())
                .employee(employeeRepository.findById(tempsDto.getIdEmployee()).get())
               // .pauses(pausesTransformer.fromDtoListToEntityList(tempsDto.getPausesDto()))
                .build();
    }

    @Override
    public List<TempsDto> fromEntityListToDtoList(List<Temps> temps) {
        List<TempsDto> tempsDTOList = new ArrayList<>();

        for (Temps temp : temps) {
            tempsDTOList.add(this.fromEntityToDto(temp));
        }

        return tempsDTOList;
    }

    @Override
    public List<Temps> fromDtoListToEntityList(List<TempsDto> tempsDto) {
        List<Temps> tempsList = new ArrayList<>();

        for (TempsDto temp : tempsDto) {
            tempsList.add(this.fromDtoToEntity(temp));
        }

        return tempsList;
    }
    
    
}
