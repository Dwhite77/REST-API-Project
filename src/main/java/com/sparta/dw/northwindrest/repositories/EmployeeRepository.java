package com.sparta.dw.northwindrest.repositories;

import com.sparta.dw.northwindrest.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}