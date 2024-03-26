package com.enigma.inventoryapps.controller;

import com.enigma.inventoryapps.constant.AppPath;
import com.enigma.inventoryapps.model.request.AdminRequest;
import com.enigma.inventoryapps.model.request.ItemRequest;
import com.enigma.inventoryapps.model.response.AdminResponse;
import com.enigma.inventoryapps.model.response.CommonResponse;
import com.enigma.inventoryapps.model.response.ItemResponse;
import com.enigma.inventoryapps.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ITEM)
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    ResponseEntity<?> createItem(@RequestBody ItemRequest itemRequest){
        ItemResponse itemResponse = itemService.create(itemRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ItemResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created item")
                        .data(itemResponse)
                        .build());
    }

    @GetMapping(AppPath.GET_BY_ID)
    ResponseEntity<?> getItemById(@PathVariable String id){
        ItemResponse itemResponse = itemService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ItemResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get item by id")
                        .data(itemResponse)
                        .build());
    }

    @GetMapping
    ResponseEntity<?> getAllItem(){
        List<ItemResponse> itemResponses = itemService.getAllItem();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<ItemResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all item")
                        .data(itemResponses)
                        .build());
    }

    @PutMapping
    ResponseEntity<?> updateItem(@RequestBody ItemRequest itemRequest){
        ItemResponse itemResponse = itemService.update(itemRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ItemResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated item")
                        .data(itemResponse)
                        .build());
    }

    @DeleteMapping(AppPath.GET_BY_ID)
    ResponseEntity<?> deleteItemById(@PathVariable String id){
        itemService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete item by id")
                        .build());
    }
}
