package com.employee.back_gestionTempsDeTravail.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.employee.back_gestionTempsDeTravail.dto.AdminDto;
import com.employee.back_gestionTempsDeTravail.entity.Admin;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Component
public class AdminTransformer implements Transformer<AdminDto,Admin> {

    @Override
    public AdminDto fromEntityToDto(Admin admin){
        return AdminDto.builder()
                .idAdmin(admin.getIdAdmin())
                .nom(admin.getNom())
                .prenom(admin.getPrenom())
                .email(admin.getEmail())
                .mdp(admin.getMdp())
                .image(admin.getImage())
                .build();
    }

    @Override
    public Admin fromDtoToEntity(AdminDto adminDto){
        return Admin.builder()
                .idAdmin(adminDto.getIdAdmin())
                .nom(adminDto.getNom())
                .prenom(adminDto.getPrenom())
                .email(adminDto.getEmail())
                .mdp(adminDto.getMdp())
                .image(adminDto.getImage())
                .build();
    }

    @Override
    public List<AdminDto> fromEntityListToDtoList(List<Admin> admins) {
        List<AdminDto> adminDTOList = new ArrayList<>();

        for (Admin admin : admins) {
            adminDTOList.add(this.fromEntityToDto(admin));
        }

        return adminDTOList;
    }

    @Override
    public List<Admin> fromDtoListToEntityList(List<AdminDto> adminDto) {
        List<Admin> adminsList = new ArrayList<>();

        for (AdminDto admin : adminDto) {
            adminsList.add(this.fromDtoToEntity(admin));
        }

        return adminsList;
    }
    
}
