package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Admin;
import com.enigma.inventoryapps.model.entity.Demand;
import com.enigma.inventoryapps.model.response.DemandDetailResponse;
import com.enigma.inventoryapps.model.response.DemandResponse;

import java.util.List;

public class DemandMapper {

    public static DemandResponse mapToResponse(Demand demand, Admin admin, List<DemandDetailResponse> response){
        return DemandResponse.builder()
                .demandId(demand.getId())
                .staffId(demand.getStaff().getId())
                .adminId(admin.getId())
                .createdAt(demand.getCreatedAt())
                .updatedAt(demand.getUpdatedAt())
                .detailRequests(response)
                .build();
    }
}
