package com.bike.stores.dev.repository;


import com.bike.stores.dev.model.Stocks;
import com.bike.stores.dev.model.StocksIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, StocksIds> {

    List<Stocks> findByStocksIds_StoreId(int storeId);

    List<Stocks> findByStocksIds_ProductId(int productId);

}
