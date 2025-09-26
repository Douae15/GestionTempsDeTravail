package com.employee.back_gestionTempsDeTravail.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.back_gestionTempsDeTravail.dto.TempsDto;
import com.employee.back_gestionTempsDeTravail.service.TempsService;

@RestController
@RequestMapping("/temps")
@CrossOrigin(origins = "*")
public class TempsContoller {
    @Autowired
    private TempsService tempsService;

   

    @GetMapping("/afficherTemps/{id}")
    public TempsDto afficherTemps(@PathVariable Long id){
        return tempsService.afficherTemps(id);
    }

    @GetMapping("/listTemps")
    public List<TempsDto> afficherListTemps(){
        return tempsService.afficherListTemps();
    }
    
    @PostMapping("/ajouterTemps")
    public void ajouterTemps(@RequestBody TempsDto tempsDto){
        tempsService.ajouterTemps(tempsDto);
    }


    @GetMapping("/{date}")
public TempsDto findTempsByDate(@PathVariable("date") String dateStr,Long idEmployee) throws ParseException {
    Date sqlDate = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    sqlDate = new Date(dateFormat.parse(dateStr).getTime());
    return tempsService.findTempsByDate(sqlDate,idEmployee);
}

@PutMapping("/modifierTemps")
public TempsDto modifierTemps(@RequestBody TempsDto tempsDto) throws java.text.ParseException{
    return tempsService.modifierTemps(tempsDto);
}

    
}
