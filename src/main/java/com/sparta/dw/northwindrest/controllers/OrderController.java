package com.sparta.dw.northwindrest.controllers;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.dtos.OrderDTO;
import com.sparta.dw.northwindrest.entities.*;
import com.sparta.dw.northwindrest.entities.QOrderEntity;
import com.sparta.dw.northwindrest.repositories.OrderRepository;
import com.sparta.dw.northwindrest.utils.exception.ApiRequestException;
import com.sparta.dw.northwindrest.utils.mapfordto.MapOrderDTO;
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

    @Autowired
    private MapOrderDTO mapOrderDTO;


    @GetMapping("/orders/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersByID(@PathVariable Integer id) {
        return ResponseEntity.ok(mapOrderDTO.getAllOrders(orderRepository.findById(id).stream().toList()));
    }

    @GetMapping(value = "/orders")
    public Callable<ResponseEntity<List<OrderDTO>>> getAllOrders(@RequestParam(required = false) String shipCity,
                                                                    @RequestParam(required = false) String shipCountry,
                                                                    @RequestParam(required = false) String employeeID,
                                                                    @RequestParam(required = false) String q) {
        return () -> {
            QOrderEntity order = QOrderEntity.orderEntity;
            BooleanExpression booleanExpression = order.isNotNull();
            if (q != null) {
                String query = "%" + q + "%";

                BooleanExpression shipCityQuery = order.shipCity.likeIgnoreCase(query);
                BooleanExpression shipCountryQuery = order.shipCountry.likeIgnoreCase(query);
                BooleanExpression queryExpression = shipCityQuery.or(shipCountryQuery);

                booleanExpression = booleanExpression.and(queryExpression); // this needs to have query logic added
            } else {
                if (shipCountry != null) {
                    booleanExpression = booleanExpression.and(order.shipCountry.equalsIgnoreCase(shipCountry));
                }

                if (employeeID != null) {
                    booleanExpression = booleanExpression.and(order.employeeID.id.like(employeeID));
                }

                if (shipCity != null) {
                    booleanExpression = booleanExpression.and(order.shipCity.equalsIgnoreCase(shipCity));
                }

                if (employeeID != null && shipCity != null) {
                    booleanExpression = booleanExpression.and(order.shipCity.equalsIgnoreCase(shipCity)).and(order.employeeID.id.like(employeeID));
                }

                if (employeeID != null && shipCountry != null) {
                    booleanExpression = booleanExpression.and(order.shipCountry.equalsIgnoreCase(shipCountry)).and(order.employeeID.id.like(employeeID));
                }
            }

            List<OrderEntity> orderEntity = (List<OrderEntity>) orderRepository.findAll(booleanExpression);
            List<OrderDTO> orderDTOS = mapOrderDTO.getAllOrders(orderEntity);
            if(shipCountry != null|| employeeID != null || shipCity != null || q != null){
                if(booleanExpression != order.isNotNull()) {
                    if (orderDTOS.size() != 0) {
                        return ResponseEntity.ok(orderDTOS);
                    } else throw new ApiRequestException("NO RESULTS");
                }else throw new ApiRequestException("Invalid Search");
            } else return ResponseEntity.ok(orderDTOS);
        };
    }

    @GetMapping(value = "/orders/verbose")
    public Callable<ResponseEntity<List<OrderEntity>>> getAllOrdersVerbose(@RequestParam(required = false) String shipCity,
                                                                    @RequestParam(required = false) String shipCountry,
                                                                    @RequestParam(required = false) String employeeID,
                                                                    @RequestParam(required = false) String q) {
        return () -> {
            QOrderEntity order = QOrderEntity.orderEntity;
            BooleanExpression booleanExpression = order.isNotNull();
            if (q != null) {
                String query = "%" + q + "%";

                BooleanExpression shipCityQuery = order.shipCity.likeIgnoreCase(query);
                BooleanExpression shipCountryQuery = order.shipCountry.likeIgnoreCase(query);
                BooleanExpression queryExpression = shipCityQuery.or(shipCountryQuery);

                booleanExpression = booleanExpression.and(queryExpression); // this needs to have query logic added
            } else {
                if (shipCountry != null) {
                    booleanExpression = booleanExpression.and(order.shipCountry.equalsIgnoreCase(shipCountry));
                }

                if (employeeID != null) {
                    booleanExpression = booleanExpression.and(order.employeeID.id.like(employeeID));
                }

                if (shipCity != null) {
                    booleanExpression = booleanExpression.and(order.shipCity.equalsIgnoreCase(shipCity));
                }

                if (employeeID != null && shipCity != null) {
                    booleanExpression = booleanExpression.and(order.shipCity.equalsIgnoreCase(shipCity)).and(order.employeeID.id.like(employeeID));
                }

                if (employeeID != null && shipCountry != null) {
                    booleanExpression = booleanExpression.and(order.shipCountry.equalsIgnoreCase(shipCountry)).and(order.employeeID.id.like(employeeID));
                }
            }
            List<OrderEntity> orderEntity = (List<OrderEntity>) orderRepository.findAll(booleanExpression);
            if(shipCountry != null|| employeeID != null || shipCity != null || q != null){
                if(booleanExpression != order.isNotNull()){
                    if(orderEntity.size() != 0){
                        return ResponseEntity.ok(orderEntity);
                    } else throw new ApiRequestException("NO RESULTS");
                }else throw new ApiRequestException("Invalid Search");
            } else return ResponseEntity.ok(orderEntity);


        };
    }


}

