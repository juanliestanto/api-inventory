package com.enigma.inventoryapps.controller;

import com.enigma.inventoryapps.constant.AppPath;
import com.enigma.inventoryapps.model.request.AuthRequest;
import com.enigma.inventoryapps.model.request.DemandRequest;
import com.enigma.inventoryapps.model.response.CommonResponse;
import com.enigma.inventoryapps.model.response.DemandResponse;
import com.enigma.inventoryapps.model.response.RegisterResponse;
import com.enigma.inventoryapps.service.AuthService;
import com.enigma.inventoryapps.service.DemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.DEMAND)
public class DemandController {

    private final DemandService demandService;

    @PostMapping
    public ResponseEntity<?> requestDemand (@RequestBody DemandRequest demandRequest){

        DemandResponse demandResponse = demandService.requestDemand(demandRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<DemandResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Sucessfully register")
                        .data(demandResponse)
                        .build());
    }
}
