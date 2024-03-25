package com.enigma.inventoryapps.model.entity;

import com.enigma.inventoryapps.constant.DbPath;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@Table(name = DbPath.ADMIN_SCHEMA)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String phone;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
