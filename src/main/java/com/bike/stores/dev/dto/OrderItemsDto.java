package com.bike.stores.dev.dto;

import com.bike.stores.dev.model.OrderItemsIds;

public class OrderItemsDto {

    private OrderItemsIds orderItemsIds;

    private int productId;

    private int quantity;

    private float listPrice;

    private float discount;


    //cons
    public OrderItemsDto(OrderItemsIds orderItemsIds, int productId, int quantity, float listPrice, float discount) {
        this.orderItemsIds = orderItemsIds;
        this.productId = productId;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.discount = discount;
    }

    //getters and setters


    public OrderItemsIds getOrderItemsIds() {
        return orderItemsIds;
    }

    public void setOrderItemsIds(OrderItemsIds orderItemsIds) {
        this.orderItemsIds = orderItemsIds;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getListPrice() {
        return listPrice;
    }

    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
