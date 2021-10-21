package com.sparta.dw.northwindrest.repositories;

import com.sparta.dw.northwindrest.entities.EmployeeEntity;
import com.sparta.dw.northwindrest.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, String> {
}
