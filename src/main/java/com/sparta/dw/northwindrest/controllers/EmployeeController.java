package com.sparta.dw.northwindrest.controllers;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.dtos.EmployeeDTO;
import com.sparta.dw.northwindrest.entities.EmployeeEntity;

import com.sparta.dw.northwindrest.entities.QEmployeeEntity;
import com.sparta.dw.northwindrest.repositories.EmployeeRepository;
import com.sparta.dw.northwindrest.utils.MapEmployeeDTO;
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

    @Autowired
    private MapEmployeeDTO mapEmployeeDTO;

    @GetMapping(value = "/employee")
    public Callable<ResponseEntity<List<EmployeeDTO>>> getAllEmployees(@RequestParam(required = false)String q,
                                                                       @RequestParam(required = false)String employeeID,
                                                                       @RequestParam(required = false)String title){
        return () -> {
            QEmployeeEntity employee = QEmployeeEntity.employeeEntity;
            BooleanExpression booleanExpression = employee.isNotNull();
            if(q!=null){
                // this section isn't finished
                String query = q ;

//                booleanExpression = booleanExpression.and(queryExpression); // this needs to have query logic added
            } else{
                if(employeeID!=null){
                    booleanExpression = booleanExpression.and(employee.id.like(employeeID));
                }
                if(title!=null){
                    String fullTitle = title + "%"; // this allows me to type sales r and get all of the sales reps back without typing in sales representatives
                    booleanExpression = booleanExpression.and(employee.title.likeIgnoreCase(fullTitle));
                }
            }
            List<EmployeeEntity> employeeEntities = (List<EmployeeEntity>) employeeRepository.findAll(booleanExpression);
            List<EmployeeDTO> employeeDTOS = mapEmployeeDTO.getAllEmployees(employeeEntities);
            return ResponseEntity.ok(employeeDTOS);
        };
    }

    @GetMapping(value = "/employee/verbose")
    public Callable<ResponseEntity<List<EmployeeEntity>>> getAllEmployeesVerbose(@RequestParam(required = false)String q,
                                                                          @RequestParam(required = false)String employeeID,
                                                                          @RequestParam(required = false)String title){
        return () -> {
            QEmployeeEntity employee = QEmployeeEntity.employeeEntity;
            BooleanExpression booleanExpression = employee.isNotNull();
            if(q!=null){
                // this section isn't finished
                String query = q ;

//                booleanExpression = booleanExpression.and(queryExpression); // this needs to have query logic added
            } else{
                if(employeeID!=null){
                    booleanExpression = booleanExpression.and(employee.id.like(employeeID));
                }
                if(title!=null){
                    String fullTitle = title + "%"; // this allows me to type sales r and get all of the sales reps back without typing in sales representatives
                    booleanExpression = booleanExpression.and(employee.title.likeIgnoreCase(fullTitle));
                }
            }
            List<EmployeeEntity> employeeEntities = (List<EmployeeEntity>) employeeRepository.findAll(booleanExpression);
            return ResponseEntity.ok(employeeEntities);
        };
    }

}
