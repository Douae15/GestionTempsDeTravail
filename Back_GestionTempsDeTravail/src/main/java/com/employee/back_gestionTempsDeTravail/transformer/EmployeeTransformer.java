package com.employee.back_gestionTempsDeTravail.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.employee.back_gestionTempsDeTravail.dto.EmployeeDto;
import com.employee.back_gestionTempsDeTravail.entity.Employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Component
public class EmployeeTransformer implements Transformer<EmployeeDto,Employee> {

    @Override
    public EmployeeDto fromEntityToDto(Employee employee){
        return EmployeeDto.builder()
                .idEmployee(employee.getIdEmployee())
                .nom(employee.getNom())
                .prenom(employee.getPrenom())
                .email(employee.getEmail())
                .mdp(employee.getMdp())
                .image(employee.getImage())
                .build();
    }

    @Override
    public Employee fromDtoToEntity(EmployeeDto employeeDto){
         return Employee.builder()
                .idEmployee(employeeDto.getIdEmployee())
                .nom(employeeDto.getNom())
                .prenom(employeeDto.getPrenom())
                .email(employeeDto.getEmail())
                .mdp(employeeDto.getMdp())
                .image(employeeDto.getImage())
                .build();
    }

    @Override
    public List<EmployeeDto> fromEntityListToDtoList(List<Employee> employees) {
        List<EmployeeDto> employeeDTOList = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDTOList.add(this.fromEntityToDto(employee));
        }

        return employeeDTOList;
    }

    @Override
    public List<Employee> fromDtoListToEntityList(List<EmployeeDto> employeeDtos) {
        List<Employee> employeesList = new ArrayList<>();

        for (EmployeeDto employeeDto : employeeDtos) {
            employeesList.add(this.fromDtoToEntity(employeeDto));
        }

        return employeesList;
    }
    
}
