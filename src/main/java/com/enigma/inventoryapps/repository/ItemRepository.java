package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
     /*In ItemRepository, I Used All Methods in JpaRepository for actions to database,
    if you are going to use one of these methods, you only call the method as necessary. For example to insert or update
    data, you can use itemRepository.save(yourEntity)
     */
}
