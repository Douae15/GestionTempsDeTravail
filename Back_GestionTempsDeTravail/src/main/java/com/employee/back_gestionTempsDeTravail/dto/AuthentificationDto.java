package com.employee.back_gestionTempsDeTravail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuthentificationDto {

    private String email;
    private String mdp;
    
}
