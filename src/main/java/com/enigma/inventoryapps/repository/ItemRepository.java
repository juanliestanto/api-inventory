package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.Item;
import com.enigma.inventoryapps.model.entity.Staff;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    //Query Native
    @Modifying
    @Query(value = """
        INSERT INTO m_item (id, name, stock, unit,is_active) VALUES
        (:#{#item.id},
        :#{#item.name},
        :#{#item.stock},
        :#{#item.unit},
        :#{#staff.isActive});
    """, nativeQuery = true)
    void insert (Item item);

    //Query Native
    @Query(value = """
        SELECT * FROM m_item WHERE id = :#{#id} AND is_active = true;
    """, nativeQuery = true)
    Optional<Item> findItem(String id);

    //Query Native
    @Query(value = """
        SELECT * FROM m_item WHERE is_active = true;
    """, nativeQuery = true)
    List<Item> findAllItem();

    //Query Native
    @Modifying
    @Transactional
    @Query(value = """
        UPDATE m_item SET 
        name = :#{#item.name}, 
        stock = :#{#item.stock},
        unit = :#{#item.unit}
        WHERE id = :#{#item.id}
    """, nativeQuery = true)
    void update(Item item);

    //Query Native
    @Modifying
    @Transactional
    @Query(value = """
        UPDATE m_item SET 
        is_active = false 
        WHERE id = :id
    """, nativeQuery = true)
    void delete(String id);
}
