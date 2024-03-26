package com.enigma.inventoryapps.model.response;

import com.enigma.inventoryapps.model.entity.DemandDetail;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class DemandResponse {
    private String demandId;
    private String staffId;
    private String adminId;
    private Long createdAt;
    private Long updatedAt;
    private List<DemandDetail> detailRequests;
}
