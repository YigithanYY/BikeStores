package com.bike.stores.dev.dto;

import com.bike.stores.dev.model.StocksIds;


public class StocksDto {

    private StocksIds stocksIds;

    private int quantity;

    public StocksDto(StocksIds stocksIds, int quantity) {
        this.stocksIds = stocksIds;
        this.quantity = quantity;
    }

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
