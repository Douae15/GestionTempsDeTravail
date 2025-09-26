package com.employee.back_gestionTempsDeTravail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.back_gestionTempsDeTravail.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(value = "SELECT * FROM employee WHERE email = ? and mdp=?", nativeQuery = true)
    Employee findEmployee(@Param("email") String email,@Param("mdp") String mdp);
    
}
