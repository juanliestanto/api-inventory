package com.enigma.inventoryapps.repository;

import com.enigma.inventoryapps.model.entity.Role;
import com.enigma.inventoryapps.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //Query Native
    @Modifying
    @Query(value = """
        INSERT INTO m_user (id,email, password, role_id) VALUES
        (:#{#user.id},
        :#{#user.email},
        :#{#user.password},
        :#{#user.role.id});
    """, nativeQuery = true)
    void insert (User user);

    //Implement Save and Flush Query Native
    @Transactional
    default void insertAndFlush(User user){
        user.setId(UUID.randomUUID().toString());
        insert(user);
        flush();
    }

    //Query Method From Jpa Repository
    Optional<User> findByEmail(String email);

    //Query Native
    @Query(value = """
        SELECT * FROM m_user WHERE id = :id
    """, nativeQuery = true)
    Optional<User> findUserId(String id);
}
