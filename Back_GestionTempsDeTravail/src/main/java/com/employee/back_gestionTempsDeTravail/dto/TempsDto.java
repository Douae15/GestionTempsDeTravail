package com.employee.back_gestionTempsDeTravail.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TempsDto {
    private Long idTemps;
    private Date date;
    private String heureDebut;
    private String heureFin;
    private List<PausesDto> pausesDto;
    private Long idEmployee;  
}
