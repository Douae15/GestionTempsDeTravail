package com.employee.back_gestionTempsDeTravail.dto;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PausesDto {

    private Long idPause;
    private Time debutPause;
    private Time finPause;
    private String statut;
 
    @JsonIgnore
    private Long idTemps;

    
    
}
