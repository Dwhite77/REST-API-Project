package com.sparta.dw.northwindrest.controllers;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.entities.EmployeeEntity;
import com.sparta.dw.northwindrest.entities.OrderEntity;
import com.sparta.dw.northwindrest.entities.QEmployeeEntity;
import com.sparta.dw.northwindrest.entities.QOrderEntity;
import com.sparta.dw.northwindrest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @GetMapping(value = "/orders")
//    public Callable<ResponseEntity<List<EmployeeEntity>>> getAllOrders(@RequestParam(required = false)String q){
//        return () -> {
//            QEmployeeEntity employee = QEmployeeEntity.employeeEntity;
//            BooleanExpression booleanExpression = employee.isNotNull();
//            if(q!=null){
//                // this section isn't finished
//                String query = q ;
//
////                booleanExpression = booleanExpression.and(queryExpression); // this needs to have query logic added
//            } else{
//                if(placeholder!=null){
//                    booleanExpression = booleanExpression.and(employee
//                }
//            }
//            List<EmployeeEntity> employeeEntities = (List<EmployeeEntity>) employeeRepository.findAll(booleanExpression);
//            return ResponseEntity.ok(employeeEntities);
//        };
//    }

}
