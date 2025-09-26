package com.employee.back_gestionTempsDeTravail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.back_gestionTempsDeTravail.dto.AdminDto;
import com.employee.back_gestionTempsDeTravail.dto.AuthentificationResponse;
import com.employee.back_gestionTempsDeTravail.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class AdminContoller {

    @Autowired
    private AdminService adminService;

    @GetMapping("/afficherAdmin/{id}")
    public AdminDto afficherAdmin(@PathVariable Long id){
        return adminService.afficherAdmin(id);
    }

    @PutMapping("/modifierAdmin/{id}")
    public ResponseEntity<AuthentificationResponse> modifierAdmin(@RequestBody AdminDto nvAdmin, @PathVariable Long id){
        AuthentificationResponse response = new AuthentificationResponse("admin", adminService.modifierAdmin(nvAdmin, id));
        return ResponseEntity.ok(response);
    }
    
}
