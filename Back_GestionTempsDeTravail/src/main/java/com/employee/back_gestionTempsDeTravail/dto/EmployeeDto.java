package com.employee.back_gestionTempsDeTravail.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
// @EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class EmployeeDto extends PersonneDto{
    private Long idEmployee;
    @JsonIgnore
    private List<TempsDto> tempsdto;
}
