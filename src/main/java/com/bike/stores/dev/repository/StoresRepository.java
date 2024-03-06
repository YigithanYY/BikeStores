package com.bike.stores.dev.repository;

import com.bike.stores.dev.model.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends JpaRepository<Stores,Integer> {
}
