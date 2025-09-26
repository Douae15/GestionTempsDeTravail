package com.employee.back_gestionTempsDeTravail.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.back_gestionTempsDeTravail.dto.EmployeeDto;
import com.employee.back_gestionTempsDeTravail.repository.EmployeeRepository;
import com.employee.back_gestionTempsDeTravail.service.EmployeeService;
import com.employee.back_gestionTempsDeTravail.transformer.EmployeeTransformer;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeTransformer employeeTransformer;

    @Override
    public EmployeeDto afficherEmployee(Long id) {
       return employeeTransformer.fromEntityToDto(employeeRepository.findById(id).get());
    }

    @Override
    public EmployeeDto modifierEmployee(EmployeeDto nvEmployee, Long id) {
        return employeeTransformer.fromEntityToDto(employeeRepository.findById(id)
                .map(employee -> {
                    employee.setNom(nvEmployee.getNom());
                    employee.setPrenom(nvEmployee.getPrenom());
                    employee.setImage(nvEmployee.getImage());
                    employee.setMdp(nvEmployee.getMdp());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    nvEmployee.setIdEmployee(id);
                    return employeeRepository.save(employeeTransformer.fromDtoToEntity(nvEmployee));
                })
        );
    }

    @Override
    public List<EmployeeDto> afficherListEmployee() {
        return employeeTransformer.fromEntityListToDtoList(employeeRepository.findAll());
    }

    @Override
    public Long getTotalEmployeeCount() {
    return employeeRepository.count();
}
    
}
