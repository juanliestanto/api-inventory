package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.constant.ERole;
import com.enigma.inventoryapps.model.entity.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO t_role (role_name) VALUES
        (:#{#role.roleName});
    """, nativeQuery = true)
    Role insert (Role role);

    default void insertAndFlush(Role role){
        insert(role);
        flush();
    }

    @Query(value = """
        SELECT * FROM t_role WHERE role_name = :#{#roleName.name()};
    """, nativeQuery = true)
    Optional<Role> findByName(ERole roleName);
}
