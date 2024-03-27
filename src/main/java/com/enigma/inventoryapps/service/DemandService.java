package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.request.DemandDetailRequest;
import com.enigma.inventoryapps.model.request.DemandRequest;
import com.enigma.inventoryapps.model.response.DemandDetailResponse;
import com.enigma.inventoryapps.model.response.DemandResponse;

import java.util.List;

public interface DemandService {

    DemandResponse requestDemand(DemandRequest demandRequest);

    DemandResponse approveDemand(String adminId, DemandDetailRequest demandDetailRequest);

    DemandResponse rejectDemand(String adminId, DemandDetailRequest demandDetailRequest);

    DemandResponse getDemandById(String id);
    List<DemandResponse> getAllDemand();
}
