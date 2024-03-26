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
    @Transactional
    @Query(value = """
        INSERT INTO m_staff (name, phone, division,is_active) VALUES
        (:#{#staff.name},
        :#{#staff.phone},
        :#{#staff.division.name()},
        :#{#staff.isActive});
    """, nativeQuery = true)
    Staff insert (Staff staff);

    @Transactional
    @Query(value = """
        SELECT * FROM m_staff WHERE id = :#{#id} AND is_active = true;
    """, nativeQuery = true)
    Optional<Staff> findStaff(String id);

    @Transactional
    @Query(value = """
        SELECT * FROM m_staff WHERE is_active = true;
    """, nativeQuery = true)
    List<Staff> findAllStaff();

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE m_staff SET 
        name = :#{#staff.name}, 
        phone = :#{#staff.phone},
        division = :#{#staff.division.name()}, 
        is_active = :#{#staff.isActive}
        WHERE id = :#{#staff.id};
    """, nativeQuery = true)
    Staff update(Staff staff);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE m_staff SET 
        is_active = false 
        WHERE id = :#{#id};
    """, nativeQuery = true)
    void delete(String id);
}