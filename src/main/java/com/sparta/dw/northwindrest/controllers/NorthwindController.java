package com.sparta.dw.northwindrest.controllers;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.entities.CustomerEntity;
import com.sparta.dw.northwindrest.entities.OrderEntity;
import com.sparta.dw.northwindrest.entities.ProductEntity;
import com.sparta.dw.northwindrest.entities.QCustomerEntity;
import com.sparta.dw.northwindrest.repositories.CustomerRepository;
import com.sparta.dw.northwindrest.repositories.OrderRepository;
import com.sparta.dw.northwindrest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@RestController
public class NorthwindController {

    //when java creates a class for us based on a specification that's a bean
    private final MyErrorController errCont = new MyErrorController();
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
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<ProductEntity> getProductsByID(@PathVariable Integer id) {
        return productRepository.findById(id);
    }

    @GetMapping("/orders")
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
//
//    @GetMapping("/orders/{id}")
//    public OrderEntity getOrdersByID(@PathVariable Integer id) {
//        return orderRepository.findById(id);
////                .orElseThrow(new ResourceNOt);
//    }

    @GetMapping(value = "/customers")
    public Callable<ResponseEntity<List<CustomerEntity>>> getAllCustomersByName(
            @RequestParam(required = false) String contactName,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String q) {
        return () -> {
            QCustomerEntity customer = QCustomerEntity.customerEntity;

            BooleanExpression booleanExpression = customer.isNotNull();

            if(q != null) {
                String query = "%" + q + "%";
                BooleanExpression compNameQuery = customer.companyName.likeIgnoreCase(query);
                BooleanExpression contactNameQuery = customer.contactName.likeIgnoreCase(query);
                BooleanExpression queryExpression = compNameQuery.or(contactNameQuery);

                booleanExpression = booleanExpression.and(queryExpression);
            } else {
                if (contactName != null) {
                    booleanExpression = booleanExpression.and(customer.contactName.eq(contactName));
                }

                if (city != null) {
                    booleanExpression = booleanExpression.and(customer.city.equalsIgnoreCase(city));
                }

                if(companyName != null) {
                    booleanExpression = booleanExpression.and(customer.companyName.eq(companyName));
                }
            }

            List<CustomerEntity> customerEntity = (List<CustomerEntity>) customerRepository.findAll(booleanExpression);

            return ResponseEntity.ok(customerEntity);
        };
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

