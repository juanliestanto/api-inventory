package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.DemandDetail;
import com.enigma.inventoryapps.model.response.DemandDetailResponse;

public class DemandDetailMapper {
    public static DemandDetailResponse mapToResponse(DemandDetail demandDetail){
        return DemandDetailResponse.builder()
                .id(demandDetail.getId())
                .demandId(demandDetail.getDemand().getId())
                .item(demandDetail.getItem())
                .quantityRequest(demandDetail.getQuantityRequest())
                .status(demandDetail.getStatus())
                .quantityApprove(demandDetail.getQuantityApprove())
                .updatedAt(demandDetail.getUpdatedAt())
                .updatedBy(demandDetail.getUpdatedBy())
                .note(demandDetail.getNote())
                .build();
    }
}
