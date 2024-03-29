package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.DemandDetail;
import com.enigma.inventoryapps.model.entity.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DemandDetailRepository extends JpaRepository<DemandDetail, String> {
    //Query Native Insert Demand Detail
    @Modifying
    @Query(value = """
        INSERT INTO trx_demand_detail (id, demand_id, item_id, quantity_request, quantity_approve, status, updated_by,updated_at) VALUES
        (:#{#demandDetail.id},
        :#{#demandDetail.demand.id},
        :#{#demandDetail.item.id},
        :#{#demandDetail.quantityRequest},
        :#{#demandDetail.quantityApprove},
        :#{#demandDetail.status.name()},
        :#{#demandDetail.updatedBy},
        :#{#demandDetail.updatedAt})
    """, nativeQuery = true)
    void insert (DemandDetail demandDetail);

    //Implement Save and Flush Query Native
    @Transactional
    default void insertAndFlush(DemandDetail demandDetail){
        demandDetail.setId(UUID.randomUUID().toString());
        insert(demandDetail);
        flush();
    }

    //Query Native Get Demand Detail By Id
    @Query(value = """
        SELECT * FROM trx_demand_detail WHERE id = :id
    """, nativeQuery = true)
    Optional<DemandDetail> findDemandDetail(String id);
}
