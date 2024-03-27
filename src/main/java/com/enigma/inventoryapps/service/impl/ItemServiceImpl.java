package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.model.entity.Item;
import com.enigma.inventoryapps.model.mapper.ItemMapper;
import com.enigma.inventoryapps.model.request.ItemRequest;
import com.enigma.inventoryapps.model.response.ItemResponse;
import com.enigma.inventoryapps.repository.ItemRepository;
import com.enigma.inventoryapps.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public ItemResponse create(ItemRequest itemRequest) {
        Item item = ItemMapper.mapToEntity(itemRequest);
        itemRepository.save(item);
        return ItemMapper.mapToResponse(item);
    }

    @Override
    public ItemResponse getById(String id) {
        Item item = this.getEntityById(id);
        return ItemMapper.mapToResponse(item);
    }

    @Override
    public Item getEntityById(String id) {
        return itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "Item Not Found"));
    }

    @Override
    public List<ItemResponse> getAllItem() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponse> responses = items.stream()
                .map(ItemMapper::mapToResponse).toList();
        return responses;
    }

    @Override
    public ItemResponse update(ItemRequest itemRequest) {
        Item itemId = this.getEntityById(itemRequest.getId());

        Item item = itemId.toBuilder()
                .name(itemRequest.getName())
                .stock(itemRequest.getStock())
                .unit(itemRequest.getUnit())
                .build();
        itemRepository.save(item);
        return ItemMapper.mapToResponse(item);
    }

    @Override
    public void delete(String id) {
        Item itemId = this.getEntityById(id);
        Item item = itemId.toBuilder()
                .isActive(false)
                .build();
        itemRepository.save(item);
    }
}
