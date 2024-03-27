package com.enigma.inventoryapps.model.entity;

import com.enigma.inventoryapps.constant.DbPath;
import com.enigma.inventoryapps.constant.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@Table(name = DbPath.DEMAN_DETAIL_SCHEMA)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DemandDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "demand_id")
    private Demand demand;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column(name = "quantity_request")
    private int quantityRequest;

    @Column(name = "quantity_approve")
    private int quantityApprove;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Long updatedAt;

    private String note;

}
