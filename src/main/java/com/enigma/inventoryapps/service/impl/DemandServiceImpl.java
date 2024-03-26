package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.constant.EStatus;
import com.enigma.inventoryapps.model.entity.*;
import com.enigma.inventoryapps.model.request.DemandRequest;
import com.enigma.inventoryapps.model.response.DemandDetailResponse;
import com.enigma.inventoryapps.model.response.DemandResponse;
import com.enigma.inventoryapps.repository.DemandRepository;
import com.enigma.inventoryapps.service.AdminService;
import com.enigma.inventoryapps.service.DemandService;
import com.enigma.inventoryapps.service.ItemService;
import com.enigma.inventoryapps.service.StaffService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandServiceImpl implements DemandService {

    private final DemandRepository demandRepository;
    private final StaffService staffService;
    private final AdminService adminService;
    private final ItemService itemService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public DemandResponse requestDemand(DemandRequest demandRequest) {
        Staff staff = staffService.getEntityById(demandRequest.getStaffId());

        List<DemandDetail> details = demandRequest.getDetailRequests().stream()
                .map(orderDetailRequest -> {
                    Item item = itemService.getEntityById(orderDetailRequest.getItemId());
                    return DemandDetail.builder()
                            .item(item)
                            .quantityRequest(orderDetailRequest.getQuantity())
                            .status(EStatus.PENDING)
                            .updatedAt(Instant.now().toEpochMilli())
                            .build();
                }).toList();

        List<DemandDetailResponse> detailResponses = details.stream()
                .map(mapping -> {
                    return DemandDetailResponse.builder()
                            .item(mapping.getItem())
                            .quantityRequest(mapping.getQuantityRequest())
                            .status(mapping.getStatus())
                            .updatedAt(Instant.now().toEpochMilli())
                            .build();
                }).toList();
        Demand demand = Demand.builder()
                .staff(staff)
                .createdAt(Instant.now().toEpochMilli())
                .updatedAt(Instant.now().toEpochMilli())
                .demandDetailList(details)
                .build();
        demandRepository.saveAndFlush(demand);

        return DemandResponse.builder()
                .demandId(demand.getId())
                .staffId(demandRequest.getStaffId())
                .createdAt(demand.getCreatedAt())
                .updatedAt(demand.getUpdatedAt())
                .detailRequests(detailResponses)
                .build();
    }
}
