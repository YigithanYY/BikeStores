package com.bike.stores.dev.repository;

import com.bike.stores.dev.dto.OrdersDto;
import com.bike.stores.dev.model.Customers;
import com.bike.stores.dev.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<OrdersDto> findByCustomerId(Customers customers);

}
