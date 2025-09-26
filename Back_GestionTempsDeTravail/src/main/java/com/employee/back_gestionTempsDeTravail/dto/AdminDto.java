package com.employee.back_gestionTempsDeTravail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
// @EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class AdminDto extends PersonneDto {
    private Long idAdmin;
}
