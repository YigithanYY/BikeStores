package com.bike.stores.dev.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class StocksIds implements Serializable {

    private static final long serialVersionUIDLONG = 1L;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "product_id")
    private int productId;

    public StocksIds(int storeId, int productId) {
        this.storeId = storeId;
        this.productId = productId;
    }

    public StocksIds() {}

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
