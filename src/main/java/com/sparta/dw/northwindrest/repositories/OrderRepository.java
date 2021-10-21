package com.sparta.dw.northwindrest.repositories;

import com.sparta.dw.northwindrest.entities.OrderEntity;


import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {

    List<OrderEntity> findAllByShipCityIsLike(String shipCity);

}