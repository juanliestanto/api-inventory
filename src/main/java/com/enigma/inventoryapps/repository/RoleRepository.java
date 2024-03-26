package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.constant.ERole;
import com.enigma.inventoryapps.model.entity.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Modifying
    @Query(value = """
        INSERT INTO t_role (id, name) VALUES
        (:#{#role.id},
        :#{#role.name.name()})
    """, nativeQuery = true)
    void insert (Role role);

    @Transactional
    default void insertAndFlush(Role role){
        role.setId(UUID.randomUUID().toString());
        insert(role);
        flush();
    }

    @Query(value = """
        SELECT * FROM t_role WHERE name = :roleName
    """, nativeQuery = true)
    Optional<Role> getRoleName(String roleName);
}
