package com.employee.back_gestionTempsDeTravail.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Personne {
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String image;   
}
