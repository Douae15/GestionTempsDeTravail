package com.employee.back_gestionTempsDeTravail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class AuthentificationResponse {
    private String role;
    private Object user;  
}
