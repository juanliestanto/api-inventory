package com.enigma.inventoryapps.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DemandDetailRequest {
    private String demandDetailId;
    private String demandId;
    private String itemId;
    private int quantity;
    private Integer quantityApprove;
    private String note;
}
