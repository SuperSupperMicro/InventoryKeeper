package com.heftyb.inventorykeeper.controllers;

import com.heftyb.inventorykeeper.models.InventoryItem;
import com.heftyb.inventorykeeper.repositories.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryItemController {

    @Autowired
    private InventoryItemRepository invRepo;

    @GetMapping(value = "/inventoryItems", produces = "application/json")
    public ResponseEntity<?> listAllInventoryItems() {
        return new ResponseEntity<>(invRepo.findAll(), HttpStatus.OK);

    }

}
