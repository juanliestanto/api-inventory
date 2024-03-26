package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandRepository extends JpaRepository<Demand, String> {
}
