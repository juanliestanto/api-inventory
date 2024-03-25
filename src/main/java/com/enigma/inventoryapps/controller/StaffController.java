package com.enigma.inventoryapps.controller;

import com.enigma.inventoryapps.constant.AppPath;
import com.enigma.inventoryapps.model.request.StaffRequest;
import com.enigma.inventoryapps.model.response.CommonResponse;
import com.enigma.inventoryapps.model.response.StaffResponse;
import com.enigma.inventoryapps.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.STAFF)
public class StaffController {

    private final StaffService staffService;

    @GetMapping(AppPath.GET_BY_ID)
    ResponseEntity<?> getStaffById(@PathVariable String id){
        StaffResponse staffResponse = staffService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<StaffResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get staff by id")
                        .data(staffResponse)
                        .build());
    }

    @GetMapping
    ResponseEntity<?> getAllStaff(){
        List<StaffResponse> staffResponses = staffService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<StaffResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all staff")
                        .data(staffResponses)
                        .build());
    }

    @PutMapping
    ResponseEntity<?> updateStaff(@RequestBody StaffRequest staffRequest){
        StaffResponse staffResponses = staffService.update(staffRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<StaffResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated staff")
                        .data(staffResponses)
                        .build());
    }

    @DeleteMapping(AppPath.GET_BY_ID)
    ResponseEntity<?> deleteStaffById(@PathVariable String id){
        staffService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete staff by id")
                        .build());
    }
}
