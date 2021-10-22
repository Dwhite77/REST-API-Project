package com.sparta.dw.northwindrest.utils.mapfordto;

import com.sparta.dw.northwindrest.dtos.SupplierDTO;
import com.sparta.dw.northwindrest.entities.SupplierEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapSupplierDTO {

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

