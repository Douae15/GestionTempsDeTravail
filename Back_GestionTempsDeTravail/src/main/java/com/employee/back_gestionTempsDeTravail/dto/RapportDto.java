package com.employee.back_gestionTempsDeTravail.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RapportDto {

    private Long idRapport;

    private Date date;
    private String path;

    private EmployeeDto Rappemployee;
    
}
