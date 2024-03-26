package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Item;
import com.enigma.inventoryapps.model.response.ItemResponse;

public class ItemMapper {

    public static ItemResponse mapToResponse(Item item){
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .stock(item.getStock())
                .unit(item.getUnit())
                .build();
    }
}
