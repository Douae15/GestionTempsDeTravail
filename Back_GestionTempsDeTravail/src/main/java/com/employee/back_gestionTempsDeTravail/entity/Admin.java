package com.employee.back_gestionTempsDeTravail.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Admin extends Personne {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idAdmin;
    
}
