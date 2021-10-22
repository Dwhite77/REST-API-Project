package com.sparta.dw.northwindrest.utils.mapfordto;

import com.sparta.dw.northwindrest.dtos.OrderDTO;
import com.sparta.dw.northwindrest.dtos.SupplierDTO;
import com.sparta.dw.northwindrest.entities.OrderEntity;
import com.sparta.dw.northwindrest.entities.SupplierEntity;
import com.sparta.dw.northwindrest.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapSupplierDTO {
    @Autowired
    private SupplierRepository supplierRepository;


    public List<SupplierDTO> getAllSuppliers(List<SupplierEntity> supplierEntityList){
        return  supplierEntityList.stream()
                .map(this::convertToSupplierDTO)
                .collect(Collectors.toList());
    }




    public SupplierDTO convertToSupplierDTO(SupplierEntity supEnt){
        SupplierDTO supplierDTO = new SupplierDTO();

        supplierDTO.setCity(supEnt.getCity());
        supplierDTO.setAddress(supEnt.getAddress());
        supplierDTO.setId(supEnt.getId());
        supplierDTO.setCompanyName(supEnt.getCompanyName());
        supplierDTO.setCountry(supEnt.getCountry());
        supplierDTO.setContactName(supEnt.getContactName());
        supplierDTO.setContactTitle(supEnt.getContactTitle());

        return supplierDTO;
    }
}

