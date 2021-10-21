package com.sparta.dw.northwindrest.repositories;

import com.sparta.dw.northwindrest.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

}