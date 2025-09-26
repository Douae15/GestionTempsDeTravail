package com.employee.back_gestionTempsDeTravail.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Employee extends Personne {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Long idEmployee;

    @OneToMany(mappedBy = "employee")
    private List<Temps> temps;

    @OneToMany(mappedBy = "Rappemployee")
    private List<Rapport> rapports;
    
}
