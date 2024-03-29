package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.entity.DemandDetail;

public interface DemanDetailService {

    DemandDetail created(DemandDetail demandDetail);

    DemandDetail updateRejected(DemandDetail demandDetail);

    DemandDetail updateApproved(DemandDetail demandDetail);

    DemandDetail findById (String id);
}
