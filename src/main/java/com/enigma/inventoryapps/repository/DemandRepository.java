package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.Demand;
import com.enigma.inventoryapps.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandRepository extends JpaRepository<Demand, String> {
    //Query Native Get Demand By Id
    @Query(value = """
        SELECT * FROM trx_demand WHERE id = :#{#id} AND is_active = true;
    """, nativeQuery = true)
    Optional<Demand> findDemand(String id);

    //Query Native Get All Demand
    @Query(value = """
        SELECT * FROM trx_demand WHERE is_active = true;
    """, nativeQuery = true)
    List<Demand> findAllDemand();
}
