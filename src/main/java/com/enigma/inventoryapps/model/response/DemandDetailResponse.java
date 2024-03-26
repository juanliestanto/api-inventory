package com.enigma.inventoryapps.model.response;

import com.enigma.inventoryapps.constant.EStatus;
import com.enigma.inventoryapps.model.entity.Demand;
import com.enigma.inventoryapps.model.entity.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DemandDetailResponse {

    private String id;

    private String demandId;

    private Item item;

    private EStatus status;

    private int quantityRequest;

    private int quantityApprove;

    private String updatedBy;

    private Long updatedAt;
}
