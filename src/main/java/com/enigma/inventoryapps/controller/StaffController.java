package com.enigma.inventoryapps.controller;

import com.enigma.inventoryapps.constant.AppPath;
import com.enigma.inventoryapps.model.request.StaffRequest;
import com.enigma.inventoryapps.model.response.CommonResponse;
import com.enigma.inventoryapps.model.response.StaffResponse;
import com.enigma.inventoryapps.service.StaffService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.STAFF)
@SecurityRequirement(name = "Bearer configuration")
public class StaffController {

    private final StaffService staffService;

    @GetMapping(AppPath.GET_BY_ID)
    ResponseEntity<?> getStaffById(@PathVariable String id){
        StaffResponse staffResponse = staffService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<StaffResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get Staff By Id")
                        .data(staffResponse)
                        .build());
    }

    @GetMapping
    ResponseEntity<?> getAllStaff(){
        List<StaffResponse> staffResponses = staffService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<StaffResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Staff")
                        .data(staffResponses)
                        .build());
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_STAFF')")
    ResponseEntity<?> updateStaff(@RequestBody StaffRequest staffRequest){
        StaffResponse staffResponses = staffService.update(staffRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<StaffResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Updated Staff")
                        .data(staffResponses)
                        .build());
    }

    @DeleteMapping(AppPath.GET_BY_ID)
    @PreAuthorize("hasAnyRole('ROLE_STAFF')")
    ResponseEntity<?> deleteStaffById(@PathVariable String id){
        staffService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Delete Staff By Id")
                        .build());
    }
}
