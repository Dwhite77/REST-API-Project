package com.sparta.dw.northwindrest.repositories;

import com.sparta.dw.northwindrest.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

    List<CustomerEntity> findAllByCityIsLike(String city);

    List<CustomerEntity> findAllByCompanyNameIsLike(String companyName);

    List<CustomerEntity> findAllByContactNameIsLike(String contactName);
}