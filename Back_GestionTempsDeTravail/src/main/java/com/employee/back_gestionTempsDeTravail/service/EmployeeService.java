package com.employee.back_gestionTempsDeTravail.service;

import java.util.List;

import com.employee.back_gestionTempsDeTravail.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto afficherEmployee(Long id);
    EmployeeDto modifierEmployee(EmployeeDto nvEmployee, Long id);
    List<EmployeeDto> afficherListEmployee();
    Long getTotalEmployeeCount();
}
