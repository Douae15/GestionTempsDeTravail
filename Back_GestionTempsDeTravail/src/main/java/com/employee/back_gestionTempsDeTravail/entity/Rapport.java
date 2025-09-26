package com.employee.back_gestionTempsDeTravail.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRapport;

    private Date date;
    private String path;

    @ManyToOne
    @JoinColumn(name = "idEmployee")
    private Employee Rappemployee;
}
