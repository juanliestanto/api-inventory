package com.enigma.inventoryapps.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ItemResponse {

    private String id;
    private String name;
    private int stock;
    private String unit;
}
