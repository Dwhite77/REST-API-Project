package com.sparta.dw.northwindrest.controllers;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.dtos.CustomerDTO;
import com.sparta.dw.northwindrest.entities.CustomerEntity;
import com.sparta.dw.northwindrest.entities.QCustomerEntity;
import com.sparta.dw.northwindrest.repositories.CustomerRepository;
import com.sparta.dw.northwindrest.utils.exception.ApiRequestException;
import com.sparta.dw.northwindrest.utils.exception.ResponseExceptionHandler;
import com.sparta.dw.northwindrest.utils.mapfordto.MapCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

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

    @Autowired
    private MapCustomerDTO mapCustomerDTO;

    @Autowired
    private ResponseExceptionHandler responseExceptionHandler;

    @GetMapping(value = "/customers")
    public Callable<ResponseEntity<List<CustomerDTO>>> getAllCustomers(@RequestParam(required = false) String contactName,
                                                                       @RequestParam(required = false) String companyName,
                                                                       @RequestParam(required = false) String city,
                                                                       @RequestParam(required = false) String country,
                                                                       @RequestParam(required = false) String q) {
        return () -> {
            QCustomerEntity customer = QCustomerEntity.customerEntity;
            BooleanExpression booleanExpression = customer.isNotNull();

            booleanExpression = customerLogic(booleanExpression, customer, city, q, contactName, companyName, country);

            List<CustomerEntity> customerEntity = (List<CustomerEntity>) customerRepository.findAll(booleanExpression);
            List<CustomerDTO> customerDTOS = mapCustomerDTO.getAllCustomers(customerEntity);

            if(contactName != null || companyName != null || city != null || country != null || q != null){
                if(booleanExpression != customer.isNotNull()) {
                    if (customerDTOS.size() != 0) {
                        return ResponseEntity.ok(customerDTOS);
                    } else throw new ApiRequestException("NO RESULTS");
                }else throw new ApiRequestException("Invalid Search");
            } else return ResponseEntity.ok(customerDTOS);


        };


    }


    @GetMapping(value = "/customers/verbose")
    public Callable<ResponseEntity<List<CustomerEntity>>> getAllCustomersVerbose(@RequestParam(required = false) String contactName,
                                                                                 @RequestParam(required = false) String companyName,
                                                                                 @RequestParam(required = false) String city,
                                                                                 @RequestParam(required = false) String country,
                                                                                 @RequestParam(required = false) String q) {
        return () -> {
            QCustomerEntity customer = QCustomerEntity.customerEntity;

            BooleanExpression booleanExpression = customer.isNotNull();

            booleanExpression = customerLogic(booleanExpression, customer, city, q, contactName, companyName, country);

            List<CustomerEntity> customerEntity = (List<CustomerEntity>) customerRepository.findAll(booleanExpression);
            if(contactName != null || companyName != null || city != null || country != null || q != null){
                if(booleanExpression != customer.isNotNull()) {
                    if(customerEntity.size() != 0){
                        return ResponseEntity.ok(customerEntity);
                    } else throw new ApiRequestException("NO RESULTS");
                }else throw new ApiRequestException("Invalid Search");
            } else return ResponseEntity.ok(customerEntity);

        };
    }




    public BooleanExpression customerLogic(BooleanExpression booleanExpression, QCustomerEntity customer,String city,  String q, String contactName, String companyName, String country){
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
        return booleanExpression;
    }

}

