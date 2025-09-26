package com.employee.back_gestionTempsDeTravail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.back_gestionTempsDeTravail.dto.PausesDto;
import com.employee.back_gestionTempsDeTravail.service.PausesService;

@RestController
@RequestMapping("/pauses")
@CrossOrigin(origins = "*")
public class PausesContoller {
    @Autowired
    private PausesService pausesService;

    @GetMapping("/afficherPause/{id}")
    public PausesDto afficherPause(@PathVariable  Long id){
        return pausesService.afficherPause(id);
    }

    @GetMapping("/listPauses")
    public List<PausesDto> afficherListPauses(){
        return pausesService.afficherListPauses();
    }

    @PutMapping("/modifierPause/{idEmployee}")
    public PausesDto modifierPauses(@PathVariable Long idEmployee){
        return pausesService.modifierPauses(idEmployee);
    }
}
