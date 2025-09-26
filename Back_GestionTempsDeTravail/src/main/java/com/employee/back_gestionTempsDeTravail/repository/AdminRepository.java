package com.employee.back_gestionTempsDeTravail.repository;

import org.springframework.stereotype.Repository;

import com.employee.back_gestionTempsDeTravail.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query(value = "SELECT * FROM admin WHERE email = ? and mdp=?", nativeQuery = true)
    Admin findAdmin(@Param("email") String email,@Param("mdp") String mdp);
    
}
