package com.sparta.dw.northwindrest.controllers;


import com.sparta.dw.northwindrest.entities.CustomerEntity;
import com.sparta.dw.northwindrest.entities.OrderEntity;
import com.sparta.dw.northwindrest.entities.ProductEntity;
import com.sparta.dw.northwindrest.repositories.CustomerRepository;
import com.sparta.dw.northwindrest.repositories.OrderRepository;
import com.sparta.dw.northwindrest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class NorthwindController {

    //when java creates a class for us based on a specification that's a bean

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public NorthwindController(ProductRepository productRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/products")
    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<ProductEntity> getProductsByID(@PathVariable Integer id){
        return productRepository.findById(id);
    }

    @GetMapping("/orders")
    public List<OrderEntity> getAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public Optional<OrderEntity> getOrdersByID(@PathVariable Integer id){
        return orderRepository.findById(id);
    }

    @GetMapping("/customers")
    @ResponseBody()
    public List<CustomerEntity> getAllCustomers(@RequestParam(required = false)String name){
        if(name == null){
            return customerRepository.findAll();
        }
        return customerRepository.findAll()
                .stream()
                .filter(customersEntity -> customersEntity.getContactName().contains(name))
                .collect(Collectors.toList());

    }



}
