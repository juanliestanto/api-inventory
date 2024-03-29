package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.model.entity.DemandDetail;
import com.enigma.inventoryapps.repository.DemandDetailRepository;
import com.enigma.inventoryapps.service.DemanDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DemandDetailServiceImpl implements DemanDetailService {

    private final DemandDetailRepository demandDetailRepository;

    @Override
    public DemandDetail created(DemandDetail demandDetail) {
        demandDetailRepository.insertAndFlush(demandDetail);
        return demandDetail;
    }

    @Override
    public DemandDetail findById(String id) {
        return demandDetailRepository.findDemandDetail(id).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "Demand Detail Not Found"));
    }
}
