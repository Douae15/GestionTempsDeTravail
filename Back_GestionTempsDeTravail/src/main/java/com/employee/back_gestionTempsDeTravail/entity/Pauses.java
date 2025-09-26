package com.employee.back_gestionTempsDeTravail.entity;

import java.sql.Time;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pauses {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPause;
     
    private Time debutPause;
    private Time finPause;
    private String statut;
 
    @ManyToOne
    @JoinColumn(name = "idTemps")
    private Temps temps;
}
