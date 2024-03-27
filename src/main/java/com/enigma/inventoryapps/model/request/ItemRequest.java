package com.enigma.inventoryapps.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(toBuilder = true)
public class ItemRequest {
    private String id;
    private String name;
    private int stock;
    private String unit;
}
