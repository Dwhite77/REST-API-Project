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
    private MyErrorController errCont = new MyErrorController();
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

//    @GetMapping("/customers")
//    @ResponseBody()
//    public List<CustomerEntity> getAllCustomers(){
//        return customerRepository.findAll();
//    }

    @GetMapping("/customers")
    @ResponseBody()
    public List<CustomerEntity> getAllCustomersByName(@RequestParam(required = false)String name, @RequestParam(required = false)String companyName, @RequestParam(required = false)String city){
        if(name == null && companyName==null && city==null){
            return customerRepository.findAll();
        }
        if(name!=null){
            return customerRepository.findAll()
                    .stream()
                    .filter(customersEntity -> customersEntity.getContactName().contains(name))
                    .collect(Collectors.toList());
        }
        if(city!=null){
            return customerRepository.findAll()
                    .stream()
                    .filter(customersEntity -> customersEntity.getCity().contains(city))
                    .collect(Collectors.toList());
        }

        return customerRepository.findAll()
                .stream()
                .filter(customersEntity -> customersEntity.getCompanyName().contains(companyName))
                .collect(Collectors.toList());
    }



//    @GetMapping("/customers/city")
//    @ResponseBody()
//    public List<CustomerEntity> getAllCustomersByCity(@RequestParam(required = false)String city){
//        if(city == null){
//            return customerRepository.findAll();
//        }
//        return customerRepository.findAll()
//                .stream()
//                .filter(customersEntity -> customersEntity.getCity().contains(city))
//                .collect(Collectors.toList());
//    }

// split by country

}

