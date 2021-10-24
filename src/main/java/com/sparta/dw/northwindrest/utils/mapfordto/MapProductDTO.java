package com.sparta.dw.northwindrest.utils.mapfordto;


import com.sparta.dw.northwindrest.dtos.ProductDTO;
import com.sparta.dw.northwindrest.entities.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MapProductDTO {

    @Autowired
    private MapSupplierDTO mapSupplierDTO;


    public List<ProductDTO> getAllProducts(List<ProductEntity> productEntityList){
        return  productEntityList.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }




    private ProductDTO convertToProductDTO(ProductEntity prodEnt){
        ProductDTO prodDTO = new ProductDTO();

        prodDTO.setId(prodEnt.getId());
        prodDTO.setProductName(prodEnt.getProductName());
        prodDTO.setDiscontinued(prodEnt.getDiscontinued());
        prodDTO.setCategoryID(prodEnt.getCategoryID());
        prodDTO.setReorderLevel(prodEnt.getReorderLevel());
        prodDTO.setSupplierID(mapSupplierDTO.convertToSupplierDTO(prodEnt.getSupplierID()));
        prodDTO.setUnitPrice(prodEnt.getUnitPrice());
        prodDTO.setUnitsInStock(prodEnt.getUnitsInStock());
        prodDTO.setQuantityPerUnit(prodEnt.getQuantityPerUnit());


        return prodDTO;
    }
}
