package com.sparta.dw.northwindrest.utils.mapfordto;

import com.sparta.dw.northwindrest.dtos.CustomerDTO;
import com.sparta.dw.northwindrest.entities.CustomerEntity;
import com.sparta.dw.northwindrest.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapCustomerDTO {

    public List<CustomerDTO> getAllCustomers(List<CustomerEntity> customerEntityList){
        return  customerEntityList.stream()
                .map(this::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO convertToCustomerDTO(CustomerEntity custEnt){
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCustomerID(custEnt.getId());
        customerDTO.setCompanyName(custEnt.getCompanyName());
        customerDTO.setContactName(custEnt.getContactName());
        customerDTO.setContactTitle(custEnt.getContactTitle());
        customerDTO.setAddress(custEnt.getAddress());
        customerDTO.setCountry(custEnt.getCountry());
        customerDTO.setCity(custEnt.getCity());

        return customerDTO;
    }
}
