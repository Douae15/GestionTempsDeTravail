package com.employee.back_gestionTempsDeTravail.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.back_gestionTempsDeTravail.dto.AdminDto;
import com.employee.back_gestionTempsDeTravail.repository.AdminRepository;
import com.employee.back_gestionTempsDeTravail.service.AdminService;
import com.employee.back_gestionTempsDeTravail.transformer.AdminTransformer;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminTransformer adminTransformer;

    @Override
    public AdminDto afficherAdmin(Long id) {
        return adminTransformer.fromEntityToDto(adminRepository.findById(id).get());
    }

    @Override
    public AdminDto modifierAdmin(AdminDto nvAdmin, Long id) {
        return adminTransformer.fromEntityToDto(adminRepository.findById(id)
                .map(admin -> {
                    admin.setNom(nvAdmin.getNom());
                    admin.setPrenom(nvAdmin.getPrenom());
                    admin.setImage(nvAdmin.getImage());
                    admin.setMdp(nvAdmin.getMdp());
                    return adminRepository.save(admin);
                })
                .orElseGet(() -> {
                    nvAdmin.setIdAdmin(id);
                    return adminRepository.save(adminTransformer.fromDtoToEntity(nvAdmin));
                })
            );
    }
    
}
