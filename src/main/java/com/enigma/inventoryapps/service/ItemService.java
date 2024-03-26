package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.entity.Item;
import com.enigma.inventoryapps.model.request.ItemRequest;
import com.enigma.inventoryapps.model.response.ItemResponse;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    ItemResponse create (ItemRequest itemRequest);
    ItemResponse getById(String id);
    Item getEntityById(String id);
    List<ItemResponse> getAllItem();
    ItemResponse update(ItemRequest itemRequest);
    void delete(String id);

}
