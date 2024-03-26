package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.request.DemandRequest;
import com.enigma.inventoryapps.model.response.DemandResponse;

public interface DemandService {

    DemandResponse requestDemand(DemandRequest demandRequest);
}
