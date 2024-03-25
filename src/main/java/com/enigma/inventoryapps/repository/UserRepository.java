package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.Role;
import com.enigma.inventoryapps.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO m_user (email, password, role) VALUES
        (:#{#user.email},
        :#{#user.password},
        :#{#user.role.name});
    """, nativeQuery = true)
    User insert (User user);

    default void insertAndFlush(User user){
        insert(user);
        flush();
    }

    @Transactional
    @Query(value = """
        SELECT * FROM m_user WHERE email = :#{#email};
    """, nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Transactional
    @Query(value = """
        SELECT * FROM m_user WHERE id = :#{#id};
    """, nativeQuery = true)
    Optional<User> findById(String id);
}
