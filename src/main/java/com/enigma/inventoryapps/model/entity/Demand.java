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
@Table(name = DbPath.DEMAND_SCHEMA)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @OneToMany(mappedBy = "demand", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<DemandDetail> demandDetailList;
}
