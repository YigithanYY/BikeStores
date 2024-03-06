package com.bike.stores.dev.repository;

import com.bike.stores.dev.model.Staffs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffsRepository extends JpaRepository<Staffs,Integer> {

    @Query(value = "SELECT * FROM [BikeStores].[sales].[staffs] WHERE email =?",nativeQuery = true)
    Optional<Staffs> findByEmail(String email);
}
