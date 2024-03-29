package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.entity.DemandDetail;

public interface DemanDetailService {

    DemandDetail created(DemandDetail demandDetail);

    DemandDetail update(DemandDetail demandDetail);

    DemandDetail findById (String id);
}
