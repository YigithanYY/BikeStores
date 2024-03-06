package com.bike.stores.dev.repository;

import com.bike.stores.dev.model.OrderItems;
import com.bike.stores.dev.model.OrderItemsIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, OrderItemsIds> {

    List<OrderItems> findByOrderItemsIds_OrderIdAndOrderItemsIds_ItemId(int orderId, int itemId);

    List<OrderItems> findByOrderItemsIds_OrderId(int orderId);

    @Query("SELECT oi FROM OrderItems oi WHERE oi.id.orderId = :orderId AND oi.id.itemId = :itemId")
    List<OrderItems> findByIds(int orderId, int itemId);
}

