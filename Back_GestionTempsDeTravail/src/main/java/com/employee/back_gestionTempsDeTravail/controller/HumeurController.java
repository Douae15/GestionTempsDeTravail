package com.employee.back_gestionTempsDeTravail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.back_gestionTempsDeTravail.dto.HumeurDto;
import com.employee.back_gestionTempsDeTravail.service.HumeurService;
import com.employee.back_gestionTempsDeTravail.token.TokenService;

@RestController
@RequestMapping("/humeur")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class HumeurController {

    @Autowired
    private HumeurService humeurService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/ListHumeur")
    public List<HumeurDto> afficherListHumeur(){
        return humeurService.afficherListHumeur();
    }

    @PostMapping("/ajouter")
    public void ajouterHumeur(@RequestBody HumeurDto humeur){
        if(tokenService.isTokenValid(humeur.getToken())){
        humeurService.ajouterHumeur(humeur);
        }
    }
    
}
