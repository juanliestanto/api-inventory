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
import java.util.ArrayList;
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
        //TODO VALIDATE STAFF BY ID
        Staff staff = staffService.getEntityById(demandRequest.getStaffId());


        //TODO SET DEMAND DETAIL REQUEST TO DEMAND DETAIL
        List<DemandDetail> details = demandRequest.getDetailRequests().stream()
                .map(orderDetailRequest -> {

                    //TODO VALIDATE ITEM BY ID
                    Item item = itemService.getEntityById(orderDetailRequest.getItemId());
                    // TODO CHECK WHETHER THE QUANTITY RECEIVED IS NOT GREATER THAN THE DEMAND QUANTITY AND STOCK OF ITEMS
                    if(orderDetailRequest.getQuantity() > item.getStock()) throw new DataIntegrityViolationException("BAD REQUEST(nominal value is greater than the limit)");
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

        demandRepository.insertAndFlush(demand);

        //TODO SET DEMAND TO DEMAND DETAIL
        List<DemandDetail> updateDemandDetails = details.stream()
                .map(demands -> {
                    demands.setDemand(demand);
                    demanDetailService.created(demands);
                    return demands;
                })
                .toList();

        return DemandMapper.mapToResponse(demand, updateDemandDetails);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public DemandResponse approveDemand(String adminId, DemandDetailRequest demandDetailRequest) {
        //TODO VALIDATE ADMIN BY ID
        Admin admin = adminService.getEntityById(adminId);

        //TODO VALIDATE DEMAND BY ID
        Demand demand = demandRepository.findById(demandDetailRequest.getDemandId()).orElseThrow(()->new ResponseStatusException((HttpStatus.NOT_FOUND),"Demand Not Found"));

        //TODO VALIDATE DEMAND DETAIL BY ID
        DemandDetail demandDetail = demanDetailService.findById(demandDetailRequest.getDemandDetailId());

        // TODO CHECK WHETHER THE QUANTITY RECEIVED IS NOT GREATER THAN THE DEMAND QUANTITY AND STOCK OF ITEMS
        if(demandDetail.getQuantityRequest() <= demandDetailRequest.getQuantityApprove() && demandDetail.getItem().getStock() <= demandDetailRequest.getQuantityApprove()) throw new DataIntegrityViolationException("BAD REQUEST(nominal value is greater than the limit)");

        // TODO SET NEW STOCK ITEM
        Item item = itemService.getEntityById(demandDetail.getItem().getId());
        item.setStock(item.getStock() - demandDetailRequest.getQuantityApprove());

        //TODO SET DEMAND DETAIL STATUS TO APPROVE AND SET QUANTITY APPROVE
        DemandDetail responseDemandDetail = demandDetail.toBuilder()
                .status(EStatus.APPROVED)
                .quantityApprove(demandDetailRequest.getQuantityApprove())
                .updatedAt(Instant.now().toEpochMilli())
                .updatedBy(admin.getName())
                .note(demandDetailRequest.getNote())
                .item(item)
                .build();

        demand.setUpdatedAt(responseDemandDetail.getUpdatedAt());
        demanDetailService.updateApproved(responseDemandDetail);
        demandRepository.saveAndFlush(demand);

        //TODO MAPPING DEMAND DETAIL TO DEMAND DETAIL RESPONSE
        List<DemandDetailResponse> response = demand.getDemandDetailList().stream()
                .map(DemandDetailMapper::mapToResponse).toList();

        return DemandMapper.mapToResponseTransaction(demand, admin, response);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public DemandResponse rejectDemand(String adminId, DemandDetailRequest demandDetailRequest) {
        //TODO VALIDATE ADMIN BY ID
        Admin admin = adminService.getEntityById(adminId);

        //TODO VALIDATE DEMAND BY ID
        Demand demand = demandRepository.findById(demandDetailRequest.getDemandId()).orElseThrow(()->new ResponseStatusException((HttpStatus.NOT_FOUND),"Demand Not Found"));

        //TODO VALIDATE DEMAND DETAIL BY ID
        DemandDetail demandDetail = demanDetailService.findById(demandDetailRequest.getDemandDetailId());

        //TODO SET DEMAND DETAIL STATUS TO REJECTED
        DemandDetail responseDemandDetail = demandDetail.toBuilder()
                .status(EStatus.REJECTED)
                .quantityApprove(0)
                .updatedAt(Instant.now().toEpochMilli())
                .updatedBy(admin.getName())
                .note(demandDetailRequest.getNote())
                .build();

        demand.setUpdatedAt(responseDemandDetail.getUpdatedAt());
        demanDetailService.updateRejected(responseDemandDetail);
        demandRepository.saveAndFlush(demand);

        //TODO MAPPING DEMAND DETAIL TO DEMAND DETAIL RESPONSE
        List<DemandDetailResponse> response = demand.getDemandDetailList().stream()
                .map(DemandDetailMapper::mapToResponse).toList();

        return DemandMapper.mapToResponseTransaction(demand, admin, response);
    }

    @Override
    public DemandResponse getDemandById(String id) {
        Demand demand = demandRepository.findDemand(id).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND),"Demand Not Found"));
        return DemandMapper.mapToResponse(demand, demand.getDemandDetailList());
    }

    @Override
    public List<DemandResponse> getAllDemand() {
        List<Demand> demand = demandRepository.findAllDemand();
        List<DemandResponse> response = new ArrayList<>();

        for(Demand demands : demand){
            response.add(DemandMapper.mapToResponse(demands, demands.getDemandDetailList()));
        }
        return response;
    }
}
