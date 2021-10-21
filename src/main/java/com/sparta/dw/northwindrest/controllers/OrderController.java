package com.sparta.dw.northwindrest.controllers;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.entities.*;
import com.sparta.dw.northwindrest.entities.QOrderEntity;
import com.sparta.dw.northwindrest.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;


//---------------------------------------------------------
// Controller for orders
//---------------------------------------------------------
@RestController
public class OrderController {


    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }



//    @GetMapping("/orders") // flush this out in a similar way to customers
//    public List<OrderEntity> getAllOrdersOld() {
//        return orderRepository.findAll();
//    }

    @GetMapping("/orders/{id}")
    public Optional<OrderEntity> getOrdersByID(@PathVariable Integer id) {
        return orderRepository.findById(id);
    }

    @GetMapping(value = "/orders")
    public Callable<ResponseEntity<List<OrderEntity>>> getAllOrders(@RequestParam(required = false)String shipCity,
                                                                    @RequestParam(required = false)String employeeID,
                                                                    @RequestParam(required = false)String q){
        return () -> {
            QOrderEntity order = QOrderEntity.orderEntity;
            BooleanExpression booleanExpression = order.isNotNull();
            if(q!=null){
                // this section isn't finished
                String query = q ;
//                booleanExpression = booleanExpression.and(queryExpression); // this needs to have query logic added
            } else{
                if(employeeID !=null){
                    booleanExpression = booleanExpression.and(order.employeeID.id.like(employeeID));
                }
                if(shipCity!=null){
                    booleanExpression = booleanExpression.and(order.shipCity.equalsIgnoreCase(shipCity));
                }
            }
            List<OrderEntity> orderEntity = (List<OrderEntity>) orderRepository.findAll(booleanExpression);
            return ResponseEntity.ok(orderEntity);
        };
    }







}

