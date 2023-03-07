package com.heftyb.inventorykeeper.controllers;

import com.heftyb.inventorykeeper.models.InventoryItem;
import com.heftyb.inventorykeeper.services.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/inventoryItems")
public class InventoryItemController {

    @Autowired
    private InventoryItemService invService;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> listAllInventoryItems() {
        return new ResponseEntity<>(invService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getInventoryItemById(@PathVariable long id) {
        InventoryItem inventoryItem = invService.findInventoryItemById(id);

        return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> addNewInventoryItem(@RequestBody InventoryItem inventoryItem) {
        inventoryItem.setInventoryItemId(0);
        inventoryItem = invService.save(inventoryItem);

        return new ResponseEntity<>(inventoryItem.getInventoryItemId(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<?> updateFullItem(@RequestBody InventoryItem inventoryItem, @PathVariable long id) {
        inventoryItem.setInventoryItemId(id);
        invService.save(inventoryItem);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<?> updateItem(@RequestBody InventoryItem inventoryItem, @PathVariable long id) {
        invService.update(inventoryItem, id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById (@PathVariable long id) {
        invService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
