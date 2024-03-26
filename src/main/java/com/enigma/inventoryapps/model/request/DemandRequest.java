package com.enigma.inventoryapps.model.request;

import com.enigma.inventoryapps.model.entity.DemandDetail;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class DemandRequest {
    private String staffId;
    private String adminId;
    private Long createdAt;
    private Long updatedAt;
    private List<DemandDetailRequest> detailRequests;
}
