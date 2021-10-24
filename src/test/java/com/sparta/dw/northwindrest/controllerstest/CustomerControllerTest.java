package com.sparta.dw.northwindrest.controllerstest;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.controllers.CustomerController;
import com.sparta.dw.northwindrest.entities.QCustomerEntity;
import com.sparta.dw.northwindrest.repositories.CustomerRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CustomerControllerTest {


    private CustomerRepository customerRepository;
    private final CustomerController customerController = new CustomerController(customerRepository);


    @Test
    public void CustomerLogicTestNull(){

        QCustomerEntity customer = QCustomerEntity.customerEntity;
        BooleanExpression booleanExpression = customer.isNotNull();
        BooleanExpression tempBoolEx = booleanExpression;
        booleanExpression = customerController.customerLogic(booleanExpression,customer,null,null,null,null,null);
        Assertions.assertEquals(tempBoolEx,booleanExpression);
    }

    @Test
    public void CustomerLogicTestCityNotNull(){
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        BooleanExpression booleanExpression = customer.isNotNull();
        BooleanExpression tempBoolEx = booleanExpression;
        booleanExpression = customerController.customerLogic(booleanExpression,customer,"London",null,null,null,null);
//        System.out.println(booleanExpression);
        Assertions.assertNotEquals(tempBoolEx,booleanExpression);
    }

    @Test
    public void CustomerLogicTestQNotNull(){
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        BooleanExpression booleanExpression = customer.isNotNull();
        BooleanExpression tempBoolEx = booleanExpression;
        booleanExpression = customerController.customerLogic(booleanExpression,customer,null,"zz",null,null,null);
//        System.out.println(booleanExpression);
        Assertions.assertNotEquals(tempBoolEx,booleanExpression);
    }

    @Test
    public void CustomerLogicTestContactNameNotNull(){
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        BooleanExpression booleanExpression = customer.isNotNull();
        BooleanExpression tempBoolEx = booleanExpression;
        booleanExpression = customerController.customerLogic(booleanExpression,customer,null,null,"Dan",null,null);
//        System.out.println(booleanExpression);
        Assertions.assertNotEquals(tempBoolEx,booleanExpression);
    }

    @Test
    public void CustomerLogicTestCompanyNameNotNull(){
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        BooleanExpression booleanExpression = customer.isNotNull();
        BooleanExpression tempBoolEx = booleanExpression;
        booleanExpression = customerController.customerLogic(booleanExpression,customer,null,null,null,"Exotic",null);
//        System.out.println(booleanExpression);
        Assertions.assertNotEquals(tempBoolEx,booleanExpression);
    }

    @Test
    public void CustomerLogicTestCountryNotNull(){
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        BooleanExpression booleanExpression = customer.isNotNull();
        BooleanExpression tempBoolEx = booleanExpression;
        booleanExpression = customerController.customerLogic(booleanExpression,customer,null,null,null,null,"UK");
//        System.out.println(booleanExpression);
        Assertions.assertNotEquals(tempBoolEx,booleanExpression);
    }

}
