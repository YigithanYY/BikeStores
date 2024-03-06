package com.bike.stores.dev.model;

import jakarta.persistence.*;

@Entity
@Table(name="stocks",schema = "production")
public class Stocks {

    @EmbeddedId
    private StocksIds stocksIds;

    @Column(name = "quantity")
    private int quantity;

    //cons

    public Stocks(StocksIds stocksIds, int quantity) {
        this.stocksIds = stocksIds;
        this.quantity = quantity;
    }

    public Stocks() {
    }


    //getter and setters


    public StocksIds getStocksIds() {
        return stocksIds;
    }

    public void setStocksIds(StocksIds stocksIds) {
        this.stocksIds = stocksIds;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
