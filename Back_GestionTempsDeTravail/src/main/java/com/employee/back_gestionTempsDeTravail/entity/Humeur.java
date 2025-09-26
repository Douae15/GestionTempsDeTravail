package com.employee.back_gestionTempsDeTravail.entity;

import java.sql.Date;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Humeur {

    @Id
    private String token;
    private String humeur;
    private Date date;
    
    
}
