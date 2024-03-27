package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Item;
import com.enigma.inventoryapps.model.request.ItemRequest;
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

    public static Item mapToEntity(ItemRequest itemRequest){
        return Item.builder()
                .name(itemRequest.getName())
                .stock(itemRequest.getStock())
                .unit(itemRequest.getUnit())
                .isActive(true)
                .build();
    }

    public static ItemRequest mapToRequest(Item item){
        return ItemRequest.builder()
                .id(item.getId())
                .name(item.getName())
                .stock(item.getStock())
                .unit(item.getUnit())
                .build();
    }
}
