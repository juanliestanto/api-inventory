package com.enigma.inventoryapps.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AdminResponse {
    private String id;
    private String name;
    private String phone;
}
