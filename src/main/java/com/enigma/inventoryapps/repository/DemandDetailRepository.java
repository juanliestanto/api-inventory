package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.DemandDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandDetailRepository extends JpaRepository<DemandDetail, String> {
}
