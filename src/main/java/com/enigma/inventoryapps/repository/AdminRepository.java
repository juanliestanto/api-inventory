package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.Admin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO m_admin (name, phone,is_active) VALUES
        (:#{#admin.name},
        :#{#admin.phone},
        :#{#admin.isActive});
    """, nativeQuery = true)
    Admin insert (Admin admin);

    @Transactional
    @Query(value = """
        SELECT * FROM m_admin WHERE id = :#{#id} AND is_active = true;
    """, nativeQuery = true)
    Optional<Admin> findById(String id);

    @Transactional
    @Query(value = """
        SELECT * FROM m_admin WHERE is_active = true;
    """, nativeQuery = true)
    List<Admin> findAll();

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE m_staff SET 
        name = :#{#admin.name}, 
        phone = :#{#admin.phone}, 
        is_active = :#{#admin.isActive}
        WHERE id = :#{#admin.id};
    """, nativeQuery = true)
    Admin update(Admin admin);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE m_admin SET 
        is_active = false 
        WHERE id = :#{#id};
    """, nativeQuery = true)
    void delete(String id);
}

