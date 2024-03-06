package com.bike.stores.dev.model;


import jakarta.persistence.*;



@Entity
@Table(name = "order_items",schema = "sales")
public class OrderItems {

    @EmbeddedId
    private OrderItemsIds orderItemsIds;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "list_price")
    private Float listPrice;

    @Column(name = "discount")
    private Float discount;


    public OrderItems(OrderItemsIds orderItemsIds, int productId, int quantity, Float listPrice, Float discount) {
        this.orderItemsIds = orderItemsIds;
        this.productId = productId;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.discount = discount;
    }
    public OrderItems() {

    }


    public OrderItemsIds getOrderItemsIds() {
        return orderItemsIds;
    }

    public void setOrderItemsIds(OrderItemsIds orderItemsIds) {
        this.orderItemsIds = orderItemsIds;
    }

    /*  public int getOrderId() {
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
    }*/

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

    public Float getListPrice() {
        return listPrice;
    }

    public void setListPrice(Float listPrice) {
        this.listPrice = listPrice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}
