package com.sparta.dw.northwindrest.repositories;

import com.sparta.dw.northwindrest.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}