package com.enigma.inventoryapps.controller;

import com.enigma.inventoryapps.constant.AppPath;
import com.enigma.inventoryapps.model.request.AuthRequest;
import com.enigma.inventoryapps.model.request.DemandDetailRequest;
import com.enigma.inventoryapps.model.request.DemandRequest;
import com.enigma.inventoryapps.model.response.CommonResponse;
import com.enigma.inventoryapps.model.response.DemandResponse;
import com.enigma.inventoryapps.model.response.RegisterResponse;
import com.enigma.inventoryapps.service.AuthService;
import com.enigma.inventoryapps.service.DemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                        .message("Sucessfully Request Demand")
                        .data(demandResponse)
                        .build());
    }

    @PostMapping(AppPath.APPROVED)
    public ResponseEntity<?> approvedDemand (@PathVariable String adminId, @RequestBody DemandDetailRequest demandDetailRequest){

        DemandResponse demandResponse = demandService.approveDemand(adminId, demandDetailRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<DemandResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sucessfully Approved Demand")
                        .data(demandResponse)
                        .build());
    }

    @PostMapping(AppPath.REJECTED)
    public ResponseEntity<?> rejecteDemand (@PathVariable String adminId, @RequestBody DemandDetailRequest demandDetailRequest){

        DemandResponse demandResponse = demandService.rejectDemand(adminId, demandDetailRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<DemandResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sucessfully Reject Demand")
                        .data(demandResponse)
                        .build());
    }
}
