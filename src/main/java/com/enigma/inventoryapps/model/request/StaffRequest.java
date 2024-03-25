package com.enigma.inventoryapps.model.request;

import com.enigma.inventoryapps.constant.EDivision;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class StaffRequest {
    private String id;
    private String name;
    private String phone;
    private EDivision division;
}
