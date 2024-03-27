package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.constant.EStatus;
import com.enigma.inventoryapps.model.entity.*;
import com.enigma.inventoryapps.model.mapper.DemandDetailMapper;
import com.enigma.inventoryapps.model.mapper.DemandMapper;
import com.enigma.inventoryapps.model.mapper.ItemMapper;
import com.enigma.inventoryapps.model.request.DemandDetailRequest;
import com.enigma.inventoryapps.model.request.DemandRequest;
import com.enigma.inventoryapps.model.request.ItemRequest;
import com.enigma.inventoryapps.model.response.DemandDetailResponse;
import com.enigma.inventoryapps.model.response.DemandResponse;
import com.enigma.inventoryapps.repository.DemandRepository;
import com.enigma.inventoryapps.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandServiceImpl implements DemandService {

    private final DemandRepository demandRepository;
    private final StaffService staffService;
    private final AdminService adminService;
    private final ItemService itemService;
    private final DemanDetailService demanDetailService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public DemandResponse requestDemand(DemandRequest demandRequest) {
        //TODO GET STAFF BY ID
        Staff staff = staffService.getEntityById(demandRequest.getStaffId());

        //TODO SET DEMAND DETAIL REQUEST TO DEMAND DETAIL
        List<DemandDetail> details = demandRequest.getDetailRequests().stream()
                .map(orderDetailRequest -> {

                    //TODO VALIDATE ITEM BY ID
                    Item item = itemService.getEntityById(orderDetailRequest.getItemId());
                    return DemandDetail.builder()
                            .item(item)
                            .quantityRequest(orderDetailRequest.getQuantity())
                            .status(EStatus.PENDING)
                            .updatedAt(Instant.now().toEpochMilli())
                            .build();
                }).toList();

        //TODO CREATE NEW DEMAND
        Demand demand = Demand.builder()
                .staff(staff)
                .createdAt(Instant.now().toEpochMilli())
                .updatedAt(Instant.now().toEpochMilli())
                .demandDetailList(details)
                .build();

        //TODO SET DEMAND TO DEMAND DETAIL
        List<DemandDetail> updateDemandDetails = details.stream()
                .map(demands -> {
                    demands.setDemand(demand);
                    return demands;
                })
                .toList();

        demandRepository.saveAndFlush(demand);

        //TODO MAPPING DEMAND DETAIL TO DEMAND DETAIL RESPONSE
        List<DemandDetailResponse> detailResponses = updateDemandDetails.stream()
                .map(mapping -> DemandDetailResponse.builder()
                        .id(mapping.getId())
                        .demandId(mapping.getDemand().getId())
                        .item(mapping.getItem())
                        .quantityRequest(mapping.getQuantityRequest())
                        .status(mapping.getStatus())
                        .updatedAt(Instant.now().toEpochMilli())
                        .build()).toList();

        return DemandResponse.builder()
                .demandId(demand.getId())
                .staffId(demandRequest.getStaffId())
                .createdAt(demand.getCreatedAt())
                .updatedAt(demand.getUpdatedAt())
                .detailRequests(detailResponses)
                .build();
    }

    @Override
    public DemandResponse approveDemand(String adminId, DemandDetailRequest demandDetailRequest) {
        Admin admin = adminService.getEntityById(adminId);

        Demand demand = demandRepository.findById(demandDetailRequest.getDemandId()).orElseThrow(()->new ResponseStatusException((HttpStatus.NOT_FOUND),"Demand Not Found"));

        DemandDetail demandDetail = demanDetailService.findById(demandDetailRequest.getDemandDetailId());

        if(demandDetail.getQuantityRequest() <= demandDetailRequest.getQuantityApprove()) throw new DataIntegrityViolationException("BAD REQUEST(nominal value is greater than the limit)");

        DemandDetail responseDemandDetail = demandDetail.toBuilder()
                .status(EStatus.APPROVED)
                .quantityApprove(demandDetailRequest.getQuantityApprove())
                .updatedAt(Instant.now().toEpochMilli())
                .updatedBy(admin.getName())
                .note(demandDetailRequest.getNote())
                .demand(demand)
                .build();

        demand.setUpdatedAt(responseDemandDetail.getUpdatedAt());
        demanDetailService.created(responseDemandDetail);
        demandRepository.saveAndFlush(demand);

        List<DemandDetailResponse> response = demand.getDemandDetailList().stream()
                .map(mapping -> {
                    Item item = itemService.getEntityById(mapping.getItem().getId());
                    item.setStock(mapping.getItem().getStock() - mapping.getQuantityApprove());
                    ItemRequest itemRequest = ItemMapper.mapToRequest(item);
                    itemService.create(itemRequest);
                    return DemandDetailMapper.mapToResponse(mapping);
                }).toList();

        return DemandMapper.mapToResponse(demand, admin, response);
    }

    @Override
    public DemandResponse rejectDemand(String adminId, DemandDetailRequest demandDetailRequest) {

        Admin admin = adminService.getEntityById(adminId);

        Demand demand = demandRepository.findById(demandDetailRequest.getDemandId()).orElseThrow(()->new ResponseStatusException((HttpStatus.NOT_FOUND),"Demand Not Found"));

        DemandDetail demandDetail = demanDetailService.findById(demandDetailRequest.getDemandDetailId());

        DemandDetail responseDemandDetail = demandDetail.toBuilder()
                .status(EStatus.REJECTED)
                .updatedAt(Instant.now().toEpochMilli())
                .updatedBy(admin.getName())
                .note(demandDetailRequest.getNote())
                .demand(demand)
                .build();

        demand.setUpdatedAt(responseDemandDetail.getUpdatedAt());
        demanDetailService.created(responseDemandDetail);
        demandRepository.saveAndFlush(demand);

        demand.setUpdatedAt(responseDemandDetail.getUpdatedAt());
        demanDetailService.created(responseDemandDetail);
        demandRepository.saveAndFlush(demand);

        List<DemandDetailResponse> response = demand.getDemandDetailList().stream()
                .map(DemandDetailMapper::mapToResponse).toList();

        return DemandMapper.mapToResponse(demand, admin, response);
    }
}
