package com.employee.back_gestionTempsDeTravail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class PersonneDto {

    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String image; 
    
}
