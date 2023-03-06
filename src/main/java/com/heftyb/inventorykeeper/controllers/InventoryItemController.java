package com.heftyb.inventorykeeper.controllers;

import com.heftyb.inventorykeeper.models.InventoryItem;
import com.heftyb.inventorykeeper.repositories.InventoryItemRepository;
import com.heftyb.inventorykeeper.services.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InventoryItemController {

    @Autowired
    private InventoryItemService invService;

    @GetMapping(value = "/inventoryItems", produces = "application/json")
    public ResponseEntity<?> listAllInventoryItems() {
        return new ResponseEntity<>(invService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/inventoryItems", consumes = "application/json")
    public ResponseEntity<?> addNewInventoryItem(@RequestBody InventoryItem inventoryItem) {
        inventoryItem.setInventoryitemid(0);
        inventoryItem = invService.save(inventoryItem);

        return new ResponseEntity<>(inventoryItem.getInventoryitemid(), HttpStatus.CREATED);
    }
}
