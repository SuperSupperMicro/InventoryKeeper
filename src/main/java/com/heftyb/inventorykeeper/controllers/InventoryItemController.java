package com.heftyb.inventorykeeper.controllers;

import com.heftyb.inventorykeeper.models.InventoryItem;
import com.heftyb.inventorykeeper.services.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/inventoryItems")
public class InventoryItemController {

    @Autowired
    private InventoryItemService invService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> listAllInventoryItems(Principal principal) {
        /**
         * An example of how to pull the user from the request.
         * It can also be accessed by a static call to the SecurityContextHolder
         * checking for a null principal is only needed on unprotected routes
         */
//        if (principal != null) {
//            UserAuthToken a = (UserAuthToken) principal;
//        AuthUserDetails p = (AuthUserDetails)a.getPrincipal();
//        System.out.println(p.getUser().toString());
//            System.out.println(a.getAuthorities());
//        }
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
