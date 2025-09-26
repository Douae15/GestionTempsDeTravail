package com.employee.back_gestionTempsDeTravail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.back_gestionTempsDeTravail.dto.AuthentificationDto;
import com.employee.back_gestionTempsDeTravail.dto.AuthentificationResponse;
import com.employee.back_gestionTempsDeTravail.entity.Admin;
import com.employee.back_gestionTempsDeTravail.entity.Employee;
import com.employee.back_gestionTempsDeTravail.repository.AdminRepository;
import com.employee.back_gestionTempsDeTravail.repository.EmployeeRepository;
import com.employee.back_gestionTempsDeTravail.transformer.AdminTransformer;
import com.employee.back_gestionTempsDeTravail.transformer.EmployeeTransformer;

@RestController
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class AuthentificationController {

    @GetMapping
    public String welcome(){
        return "Welcome";
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeTransformer employeeTransformer;

    @Autowired
    private AdminTransformer adminTransformer;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> authenticate(@RequestBody AuthentificationDto request) {
    Admin admin = adminRepository.findAdmin(request.getEmail(), request.getMdp());
    Employee employee = employeeRepository.findEmployee(request.getEmail(), request.getMdp());

     if (employee != null) {
        AuthentificationResponse response = new AuthentificationResponse("employee", employeeTransformer.fromEntityToDto(employee));
        return ResponseEntity.ok(response);
    }else if (admin != null) {
        AuthentificationResponse response = new AuthentificationResponse("admin", adminTransformer.fromEntityToDto(admin));
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
    
}
