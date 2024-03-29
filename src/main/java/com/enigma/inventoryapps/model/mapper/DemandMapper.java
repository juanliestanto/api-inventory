package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Admin;
import com.enigma.inventoryapps.model.entity.Demand;
import com.enigma.inventoryapps.model.entity.DemandDetail;
import com.enigma.inventoryapps.model.response.DemandDetailResponse;
import com.enigma.inventoryapps.model.response.DemandResponse;

import java.time.Instant;
import java.util.List;

public class DemandMapper {

    public static DemandResponse mapToResponseTransaction(Demand demand, Admin admin, List<DemandDetailResponse> response){
        return DemandResponse.builder()
                .demandId(demand.getId())
                .staffId(demand.getStaff().getId())
                .createdAt(demand.getCreatedAt())
                .updatedAt(demand.getUpdatedAt())
                .detailRequests(response)
                .build();
    }

    public static DemandResponse mapToResponse(Demand demand, List<DemandDetail> demandDetail){
        List<DemandDetailResponse> detailResponses = demandDetail.stream()
                .map(mapping -> DemandDetailResponse.builder()
                        .id(mapping.getId())
                        .demandId(mapping.getDemand().getId())
                        .item(mapping.getItem())
                        .quantityRequest(mapping.getQuantityRequest())
                        .quantityApprove(mapping.getQuantityApprove())
                        .status(mapping.getStatus())
                        .updatedAt(Instant.now().toEpochMilli())
                        .updatedBy(mapping.getUpdatedBy())
                        .note(mapping.getNote())
                        .build()).toList();

        return DemandResponse.builder()
                .demandId(demand.getId())
                .staffId(demand.getStaff().getId())
                .createdAt(demand.getCreatedAt())
                .updatedAt(demand.getUpdatedAt())
                .detailRequests(detailResponses)
                .build();
    }
}
