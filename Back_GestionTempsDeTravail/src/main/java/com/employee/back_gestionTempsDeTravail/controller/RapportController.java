package com.employee.back_gestionTempsDeTravail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.back_gestionTempsDeTravail.dto.RapportDto;
import com.employee.back_gestionTempsDeTravail.service.RapportService;

@RestController
@RequestMapping("/rapport")
@CrossOrigin(origins = "http://localhost:4200")
public class RapportController {
    @Autowired
    private RapportService rapportService;

    @GetMapping("/EmployeeRapport")
    public List<RapportDto> getRapportsByEmployee(Long idEmployee) {
        return rapportService.getRapportsByEmployee(idEmployee);
    }
    
}
