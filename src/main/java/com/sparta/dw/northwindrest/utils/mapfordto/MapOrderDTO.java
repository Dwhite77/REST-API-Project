package com.sparta.dw.northwindrest.utils.mapfordto;

import com.sparta.dw.northwindrest.dtos.OrderDTO;
import com.sparta.dw.northwindrest.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MapOrderDTO {

    @Autowired
    private MapCustomerDTO mapCustomerDTO;

    @Autowired
    private MapEmployeeDTO mapEmployeeDTO;


    public List<OrderDTO> getAllOrders(List<OrderEntity> orderEntityList){
        return  orderEntityList.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }




    private OrderDTO convertToOrderDTO(OrderEntity ordEnt){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setEmployeeID(mapEmployeeDTO.convertToEmployeeDTO(ordEnt.getEmployeeID())); // have a look at testing specifically these to ensure we are getting what we actually want here
        orderDTO.setCustomerID(mapCustomerDTO.convertToCustomerDTO(ordEnt.getCustomerID()));
        orderDTO.setId(ordEnt.getId());
        orderDTO.setShipAddress(ordEnt.getShipAddress());
        orderDTO.setShipCity(ordEnt.getShipCity());
        orderDTO.setShipCountry(ordEnt.getShipCountry());
        orderDTO.setShippedDate(ordEnt.getShippedDate());


        return orderDTO;
    }
}
