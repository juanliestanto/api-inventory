package com.enigma.inventoryapps.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AdminRequest {
    private String id;
    private String name;
    private String phone;
}
