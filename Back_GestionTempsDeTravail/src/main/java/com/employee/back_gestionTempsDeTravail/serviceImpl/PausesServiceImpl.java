package com.employee.back_gestionTempsDeTravail.serviceImpl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.back_gestionTempsDeTravail.dto.PausesDto;
import com.employee.back_gestionTempsDeTravail.entity.Pauses;
import com.employee.back_gestionTempsDeTravail.entity.Temps;
import com.employee.back_gestionTempsDeTravail.repository.PausesRepository;
import com.employee.back_gestionTempsDeTravail.repository.TempsRepository;
import com.employee.back_gestionTempsDeTravail.service.PausesService;
import com.employee.back_gestionTempsDeTravail.transformer.PausesTransformer;

@Service
public class PausesServiceImpl implements PausesService {
    @Autowired
    private PausesRepository pausesRepository;

    @Autowired
    private TempsRepository tempsRepository;

    @Autowired
    private PausesTransformer pausesTransformer;

    @Override
    public PausesDto afficherPause(Long id) {
       return pausesTransformer.fromEntityToDto(pausesRepository.findById(id).orElse(null));
    }

    @Override
    public List<PausesDto> afficherListPauses() {
        return pausesTransformer.fromEntityListToDtoList(pausesRepository.findAll());
    }

    @Override
    public PausesDto modifierPauses(Long idEmployee) {
        System.out.println("hello ");
        Date dateAujourdhui = new Date(System.currentTimeMillis());
        System.out.println("hello ");
        Pauses dernierePause = pausesRepository.findLastPause(idEmployee, dateAujourdhui);
        LocalTime heureActuelle = LocalTime.now();
        System.out.println("hello ");
        if (dernierePause != null) {
            System.out.println("hello ");
            if ("terminer".equals(dernierePause.getStatut())) {
                Pauses nouvellePause = new Pauses();
                nouvellePause.setTemps(dernierePause.getTemps());
                nouvellePause.setDebutPause(Time.valueOf(heureActuelle));
                nouvellePause.setStatut("non terminer");
                nouvellePause.setFinPause(null); 
                return pausesTransformer.fromEntityToDto(pausesRepository.save(nouvellePause));
            } else {
                dernierePause.setFinPause(Time.valueOf(heureActuelle));
                dernierePause.setStatut("terminer");
                return pausesTransformer.fromEntityToDto(pausesRepository.save(dernierePause));
            }
        } else {
            System.out.println("hello ok");
            Pauses nouvellePause = new Pauses();
            Temps tempsAujourdhui=tempsRepository.findByDate(dateAujourdhui,idEmployee);
            if (tempsAujourdhui != null) {
            System.out.println("hello "+ tempsAujourdhui.getHeureFin());
            nouvellePause.setTemps(tempsAujourdhui);
            nouvellePause.setDebutPause(Time.valueOf(heureActuelle));
            nouvellePause.setStatut("non terminer");
            nouvellePause.setFinPause(null);
            return pausesTransformer.fromEntityToDto(pausesRepository.save(nouvellePause));
            }else{
                return null;
            }
        }
    }
        
   
    }



