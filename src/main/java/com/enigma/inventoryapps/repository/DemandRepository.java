package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.Demand;
import com.enigma.inventoryapps.model.entity.DemandDetail;
import com.enigma.inventoryapps.model.entity.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DemandRepository extends JpaRepository<Demand, String> {

    //Query Native Insert Demand Detail
    @Modifying
    @Query(value = """
        INSERT INTO trx_demand (id, created_at, updated_at, staff_id) VALUES
        (:#{#demand.id},
        :#{#demand.createdAt},
        :#{#demand.updatedAt},
        :#{#demand.staff.id})
    """, nativeQuery = true)
    void insert (Demand demand);

    //Implement Save and Flush Query Native
    @Transactional
    default void insertAndFlush(Demand demand){
        demand.setId(UUID.randomUUID().toString());
        insert(demand);
        flush();
    }

    //Query Native Get Demand By Id
    @Query(value = """
        SELECT * FROM trx_demand WHERE id = :#{#id};
    """, nativeQuery = true)
    Optional<Demand> findDemand(String id);

    //Query Native Get All Demand
    @Query(value = """
        SELECT * FROM trx_demand;
    """, nativeQuery = true)
    List<Demand> findAllDemand();
}
