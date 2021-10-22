package com.sparta.dw.northwindrest.repositories;

import com.sparta.dw.northwindrest.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends CrudRepository<SupplierEntity, Integer> {
}