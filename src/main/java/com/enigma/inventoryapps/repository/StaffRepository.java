package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.Staff;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

    @Modifying
    @Query(value = """
        INSERT INTO m_staff (id, name, phone, division,is_active, user_id) VALUES
        (:#{#staff.id},
        :#{#staff.name},
        :#{#staff.phone},
        :#{#staff.division.name()},
        :#{#staff.isActive},
        :#{#staff.user.id});
    """, nativeQuery = true)
    void insert (Staff staff);

    @Query(value = """
        SELECT * FROM m_staff WHERE id = :#{#id} AND is_active = true;
    """, nativeQuery = true)
    Optional<Staff> findStaff(String id);

    @Query(value = """
        SELECT * FROM m_staff WHERE is_active = true;
    """, nativeQuery = true)
    List<Staff> findAllStaff();

    @Modifying
    @Query(value = """
        UPDATE m_staff SET 
        name = :#{#staff.name}, 
        phone = :#{#staff.phone},
        division = :#{#staff.division.name()}, 
        is_active = :#{#staff.isActive}
        WHERE id = :#{#staff.id}
    """, nativeQuery = true)
    void update(Staff staff);

    @Modifying
    @Query(value = """
        UPDATE m_staff SET 
        is_active = false 
        WHERE id = :id
    """, nativeQuery = true)
    void delete(String id);
}