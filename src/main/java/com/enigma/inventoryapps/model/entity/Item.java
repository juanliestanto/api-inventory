package com.enigma.inventoryapps.model.entity;

import com.enigma.inventoryapps.constant.DbPath;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@Table(name = DbPath.ITEM_SCHEMA)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private int stock;

    private String unit;

    @OneToMany(mappedBy = "item")
    @JsonBackReference
    private List<DemandDetail> demandDetailList;

    @Column(name = "is_active")
    private Boolean isActive;


}
