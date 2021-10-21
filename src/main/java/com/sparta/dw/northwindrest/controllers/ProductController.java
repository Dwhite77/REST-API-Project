package com.sparta.dw.northwindrest.controllers;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.entities.OrderEntity;
import com.sparta.dw.northwindrest.entities.ProductEntity;
import com.sparta.dw.northwindrest.entities.QOrderEntity;
import com.sparta.dw.northwindrest.entities.QProductEntity;
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

//---------------------------------------------------------
// Controller for products
//---------------------------------------------------------
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @GetMapping("/products/{id}")
    public Optional<ProductEntity> getProductsByID(@PathVariable Integer id) {
        return productRepository.findById(id);
    }


    @GetMapping(value = "/products")
    public Callable<ResponseEntity<List<ProductEntity>>> getAllOrders(@RequestParam(required = false)String supplierName,
                                                                    @RequestParam(required = false)String categoryID,
                                                                    @RequestParam(required = false)String q){
        return () -> {
            QProductEntity product = QProductEntity.productEntity;
            BooleanExpression booleanExpression = product.isNotNull();
            if(q!=null){
                // this section isn't finished
                String query = q ;
//                booleanExpression = booleanExpression.and(queryExpression); // this needs to have query logic added
            } else{

                if(categoryID !=null){
                    booleanExpression = booleanExpression.and(product.categoryID.id.like(categoryID));
                }
                if(supplierName!=null){
                    String fullSupplierName = "%" + supplierName + "%";
                    booleanExpression = booleanExpression.and(product.supplierID.companyName.likeIgnoreCase(fullSupplierName));
                }
                if(supplierName!=null&&categoryID!=null){
                    String fullSupplierName = "%" + supplierName + "%";
                    booleanExpression = booleanExpression.and(product.supplierID.companyName.likeIgnoreCase(fullSupplierName)).and(product.categoryID.id.like(categoryID));
                }
            }
            List<ProductEntity> productEntity = (List<ProductEntity>) productRepository.findAll(booleanExpression);
            return ResponseEntity.ok(productEntity);
        };
    }
}
