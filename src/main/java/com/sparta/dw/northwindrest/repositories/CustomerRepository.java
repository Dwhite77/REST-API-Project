package com.sparta.dw.northwindrest.repositories;

import com.sparta.dw.northwindrest.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
}