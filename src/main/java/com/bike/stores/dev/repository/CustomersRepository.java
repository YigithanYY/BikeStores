package com.bike.stores.dev.repository;

import com.bike.stores.dev.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CustomersRepository extends JpaRepository<Customers,Integer> {

    @Query(value = "SELECT * FROM [BikeStores].[sales].[customers] WHERE email =?",nativeQuery = true)
    Optional<Customers> findByEmail(String email);



}
