package com.employee.back_gestionTempsDeTravail.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HumeurDto {
    private String token;
    private String humeur;
    private Date date;
}
