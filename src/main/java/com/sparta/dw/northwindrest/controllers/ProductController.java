package com.sparta.dw.northwindrest.controllers;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.dw.northwindrest.dtos.ProductDTO;
import com.sparta.dw.northwindrest.entities.ProductEntity;
import com.sparta.dw.northwindrest.entities.QProductEntity;
import com.sparta.dw.northwindrest.repositories.ProductRepository;
import com.sparta.dw.northwindrest.utils.exception.ApiRequestException;
import com.sparta.dw.northwindrest.utils.mapfordto.MapProductDTO;
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

    @Autowired
    private MapProductDTO mapProductDTO;

    @GetMapping("/products/{id}")
    public Optional<ProductEntity> getProductsByID(@PathVariable Integer id) {
        return productRepository.findById(id);
    }


    @GetMapping(value = "/products")
    public Callable<ResponseEntity<List<ProductDTO>>> getAllProducts(@RequestParam(required = false) String supplierName,
                                                                     @RequestParam(required = false) String categoryID,
                                                                     @RequestParam(required = false) String stock,
                                                                     @RequestParam(required = false) String q) {
        return () -> {
            QProductEntity product = QProductEntity.productEntity;
            BooleanExpression booleanExpression = product.isNotNull();
            if (q != null) {
                String query = q;
                BooleanExpression productNameQuery = product.productName.likeIgnoreCase(query);
                BooleanExpression supplierNameQuery = product.supplierID.companyName.likeIgnoreCase(query);
                BooleanExpression queryExpression = productNameQuery.or(supplierNameQuery);

                booleanExpression = booleanExpression.and(queryExpression);
            } else {

                if (categoryID != null) {
                    booleanExpression = booleanExpression.and(product.categoryID.id.like(categoryID));
                }
                if (supplierName != null) {
                    String fullSupplierName = "%" + supplierName + "%";
                    booleanExpression = booleanExpression.and(product.supplierID.companyName.likeIgnoreCase(fullSupplierName));
                }
                if (stock != null) {
                    booleanExpression = booleanExpression.and(product.unitsInStock.like(stock));
                }
            }
            List<ProductEntity> productEntity = (List<ProductEntity>) productRepository.findAll(booleanExpression);
            List<ProductDTO> productDTOS = mapProductDTO.getAllProducts(productEntity);
            if(supplierName != null || categoryID != null|| stock != null||q != null){
                if(booleanExpression != product.isNotNull()){
                    if(productDTOS.size() != 0){
                        return ResponseEntity.ok(productDTOS);
                    } else throw new ApiRequestException("NO RESULTS");
                }else throw new ApiRequestException("Invalid Search");
            }else return ResponseEntity.ok(productDTOS);


        };
    }

    @GetMapping(value = "/products/verbose")
    public Callable<ResponseEntity<List<ProductEntity>>> getAllProductsVerbose(@RequestParam(required = false) String supplierName,
                                                                               @RequestParam(required = false) String categoryID,
                                                                               @RequestParam(required = false) String stock,
                                                                               @RequestParam(required = false) String q) {
        return () -> {
            QProductEntity product = QProductEntity.productEntity;
            BooleanExpression booleanExpression = product.isNotNull();
            if (q != null) {
                String query = q;
                BooleanExpression productNameQuery = product.productName.likeIgnoreCase(query);
                BooleanExpression supplierNameQuery = product.supplierID.companyName.likeIgnoreCase(query);
                BooleanExpression queryExpression = productNameQuery.or(supplierNameQuery);

                booleanExpression = booleanExpression.and(queryExpression);
            } else {

                if (categoryID != null) {
                    booleanExpression = booleanExpression.and(product.categoryID.id.like(categoryID));
                }
                if (supplierName != null) {
                    String fullSupplierName = "%" + supplierName + "%";
                    booleanExpression = booleanExpression.and(product.supplierID.companyName.likeIgnoreCase(fullSupplierName));
                }
                if (stock != null) {
                    booleanExpression = booleanExpression.and(product.unitsInStock.like(stock));
                }
            }
            List<ProductEntity> productEntity = (List<ProductEntity>) productRepository.findAll(booleanExpression);
            if(supplierName != null || categoryID != null|| stock != null||q != null) {
                if (booleanExpression != product.isNotNull()) {
                    if (productEntity.size() != 0) {
                        return ResponseEntity.ok(productEntity);
                    } else throw new ApiRequestException("NO RESULTS");
                } else throw new ApiRequestException("Invalid Search");
            }else return ResponseEntity.ok(productEntity);
    };
    }

    @GetMapping(value = "/products/nostock")
    // a little tester function, to see if this kind of things works, which it does
    public Callable<ResponseEntity<List<ProductDTO>>> getAllProductsNoStock() {
        return () -> {
            QProductEntity product = QProductEntity.productEntity;
            BooleanExpression booleanExpression = product.isNotNull();
            booleanExpression = booleanExpression.and(product.unitsInStock.eq(0));
            List<ProductEntity> productEntity = (List<ProductEntity>) productRepository.findAll(booleanExpression);
            List<ProductDTO> productDTOS = mapProductDTO.getAllProducts(productEntity);
            return ResponseEntity.ok(productDTOS);

        };
    }
}
