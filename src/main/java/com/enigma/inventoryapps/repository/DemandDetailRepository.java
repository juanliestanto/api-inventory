package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.DemandDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandDetailRepository extends JpaRepository<DemandDetail, String> {
    /*In DemandDetailRepository, I Used All Methods in JpaRepository for actions to database,
    if you are going to use one of these methods, you only call the method as necessary. For example to insert or update
    data, you can use demandDetailRepository.save(yourEntity).
     */
}
