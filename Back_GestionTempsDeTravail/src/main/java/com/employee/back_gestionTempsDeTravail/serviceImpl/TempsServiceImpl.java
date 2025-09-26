package com.employee.back_gestionTempsDeTravail.serviceImpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.back_gestionTempsDeTravail.dto.TempsDto;
import com.employee.back_gestionTempsDeTravail.entity.Temps;
import com.employee.back_gestionTempsDeTravail.repository.TempsRepository;
import com.employee.back_gestionTempsDeTravail.service.TempsService;
import com.employee.back_gestionTempsDeTravail.transformer.TempsTransformer;

@Service
public class TempsServiceImpl implements TempsService {

    @Autowired
    private TempsRepository tempsRepository;

    @Autowired
    private TempsTransformer tempsTransformer;

    @Override
    public TempsDto afficherTemps(Long id) {
        return tempsTransformer.fromEntityToDto(tempsRepository.findById(id).get());
    }

    @Override
    public List<TempsDto> afficherListTemps() {
        return tempsTransformer.fromEntityListToDtoList(tempsRepository.findAll());
    }


    @Override
    public void ajouterTemps(TempsDto tempsDto) {
        tempsRepository.save(tempsTransformer.fromDtoToEntity(tempsDto));
    }

    @Override
    public TempsDto findTempsByDate(Date date,Long idEmployee) {
        return tempsTransformer.fromEntityToDto(tempsRepository.findByDate(date,idEmployee));
    }

    @Override
    public TempsDto modifierTemps(TempsDto tempsDto) throws  java.text.ParseException {
        Date dateAujourdhui = new Date(System.currentTimeMillis());
        Temps tempsDaujourdui =tempsRepository.findByDate(dateAujourdhui,tempsDto.getIdEmployee());
        
        if (tempsDaujourdui != null) {
            if (tempsDaujourdui.getHeureFin()==null) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                java.util.Date heureDebutDate = sdf.parse(tempsDto.getHeureDebut());
                java.util.Date heureFinDate = sdf.parse(tempsDto.getHeureFin());
                 if (tempsDto.getHeureFin() != null && heureFinDate.after(heureDebutDate)) {
                    tempsDaujourdui.setHeureFin(tempsDto.getHeureFin());
                    return tempsTransformer.fromEntityToDto(tempsRepository.save(tempsDaujourdui));
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }else{
            this.ajouterTemps(tempsDto);
            return tempsDto;
        }
    }

}
