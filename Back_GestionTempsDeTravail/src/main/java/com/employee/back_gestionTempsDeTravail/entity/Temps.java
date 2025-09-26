package com.employee.back_gestionTempsDeTravail.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Temps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTemps;

    private Date date;
    private String heureDebut;
    private String heureFin;

    @ManyToOne
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    @OneToMany(mappedBy = "temps")
    private List<Pauses> pauses;

}
