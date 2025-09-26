package com.employee.back_gestionTempsDeTravail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.back_gestionTempsDeTravail.dto.AuthentificationResponse;
import com.employee.back_gestionTempsDeTravail.dto.EmployeeDto;
import com.employee.back_gestionTempsDeTravail.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeContoller {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/afficherEmployee/{id}")
    public EmployeeDto afficherEmployee(@PathVariable Long id){
        return employeeService.afficherEmployee(id);
    }

    @GetMapping("/listEmployee")
    public List<EmployeeDto> afficherListEmployee(){
        return employeeService.afficherListEmployee();
    }

    @PutMapping("/modifierEmployee/{id}")
    public  ResponseEntity<AuthentificationResponse> modifierEmployee(@RequestBody EmployeeDto nvEmployee, @PathVariable Long id){
        AuthentificationResponse response = new AuthentificationResponse("employee", employeeService.modifierEmployee(nvEmployee, id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total-employees")
    public Long getTotalEmployeeCount() {
        return employeeService.getTotalEmployeeCount();
    }
    
}
