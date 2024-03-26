package com.enigma.inventoryapps.model.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemRequest {
    private String id;
    private String name;
    private int stock;
    private String unit;
}
