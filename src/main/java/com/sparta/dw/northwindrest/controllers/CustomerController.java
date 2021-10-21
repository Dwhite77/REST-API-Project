package com.sparta.dw.northwindrest.controllers;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.entities.CustomerEntity;
import com.sparta.dw.northwindrest.entities.QCustomerEntity;
import com.sparta.dw.northwindrest.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;




//:TODO - i want to be able to search by: company name, contact name, contact title, city, country


//---------------------------------------------------------
// Controller for customers
//---------------------------------------------------------
@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/customers")
    public Callable<ResponseEntity<List<CustomerEntity>>> getAllCustomers(
            @RequestParam(required = false) String contactName,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String q) {
        return () -> {
            QCustomerEntity customer = QCustomerEntity.customerEntity;

            BooleanExpression booleanExpression = customer.isNotNull();

            if (q != null) {
                String query = "%" + q + "%";
                BooleanExpression compNameQuery = customer.companyName.likeIgnoreCase(query);
                BooleanExpression contactNameQuery = customer.contactName.likeIgnoreCase(query);
                BooleanExpression queryExpression = compNameQuery.or(contactNameQuery);

                booleanExpression = booleanExpression.and(queryExpression);
            } else {
                if (contactName != null) {
                    booleanExpression = booleanExpression.and(customer.contactName.containsIgnoreCase(contactName));
                }

                if (city != null) {
                    booleanExpression = booleanExpression.and(customer.city.equalsIgnoreCase(city));
                }

                if (country != null) {
                    booleanExpression = booleanExpression.and(customer.country.equalsIgnoreCase(country));
                }

                if (companyName != null) {
                    booleanExpression = booleanExpression.and(customer.companyName.containsIgnoreCase(companyName));
                }
            }
            List<CustomerEntity> customerEntity = (List<CustomerEntity>) customerRepository.findAll(booleanExpression);
            return ResponseEntity.ok(customerEntity);
        };
    }

}
