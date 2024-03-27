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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.DEMAND)
@SecurityRequirement(name = "Bearer configuration")
public class DemandController {

    private final DemandService demandService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_STAFF')")
    public ResponseEntity<?> requestDemand (@RequestBody DemandRequest demandRequest){

        DemandResponse demandResponse = demandService.requestDemand(demandRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<DemandResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Sucessfully Request Demand")
                        .data(demandResponse)
                        .build());
    }

    @PutMapping(AppPath.APPROVED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> approvedDemand (@PathVariable String adminId, @RequestBody DemandDetailRequest demandDetailRequest){

        DemandResponse demandResponse = demandService.approveDemand(adminId, demandDetailRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<DemandResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sucessfully Approved Demand")
                        .data(demandResponse)
                        .build());
    }

    @PutMapping(AppPath.REJECTED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> rejecteDemand (@PathVariable String adminId, @RequestBody DemandDetailRequest demandDetailRequest){

        DemandResponse demandResponse = demandService.rejectDemand(adminId, demandDetailRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<DemandResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sucessfully Reject Demand")
                        .data(demandResponse)
                        .build());
    }

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getDemandById(@PathVariable String id){
        DemandResponse demandResponse = demandService.getDemandById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<DemandResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sucessfully Get Demand")
                        .data(demandResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllDemand(){
        List<DemandResponse> demandResponseList = demandService.getAllDemand();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<DemandResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sucessfully Get Demand")
                        .data(demandResponseList)
                        .build());
    }
}
