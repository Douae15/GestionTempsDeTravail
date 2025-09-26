package com.employee.back_gestionTempsDeTravail.service;


import com.employee.back_gestionTempsDeTravail.dto.AdminDto;

public interface AdminService {
    AdminDto afficherAdmin(Long id);
    AdminDto modifierAdmin(AdminDto nvAdmin, Long id);
}
