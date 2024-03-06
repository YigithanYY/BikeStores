package com.bike.stores.dev.model;


import jakarta.persistence.*;

import java.io.Serializable;


@Embeddable
public class OrderItemsIds implements Serializable {

    private static final long serialVersionUIDLONG = 1L;
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "item_id")
    private int itemId;

    public OrderItemsIds(int orderId, int itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public OrderItemsIds() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
