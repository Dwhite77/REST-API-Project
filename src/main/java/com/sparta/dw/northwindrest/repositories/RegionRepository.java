package com.sparta.dw.northwindrest.repositories;

import com.sparta.dw.northwindrest.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {
}