package com.heftyb.inventorykeeper.controllers;

import com.heftyb.inventorykeeper.Auth.UserAuthToken;
import com.heftyb.inventorykeeper.models.GroceryInventoryItem;
import com.heftyb.inventorykeeper.models.User;
import com.heftyb.inventorykeeper.services.GroceryInventoryItemService;
import com.heftyb.inventorykeeper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/groceryItems")
public class GroceryInventoryItemController {

    @Autowired
    private GroceryInventoryItemService groService;

    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> listAllItems(Principal principal) {
        User u = userService.findUserById(((UserAuthToken) principal).getAuthenticatedUserId());
        return new ResponseEntity<>(u.getGroceryInventoryItems(), HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> addNewGroceryItem(@RequestBody GroceryInventoryItem item, Principal principal) {
        item.setGroceryItemId(0);
        User u = userService.findUserById(((UserAuthToken) principal).getAuthenticatedUserId());
        item.setUser(u);
        item = groService.saveItem(item);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<?> updateFullItem(@PathVariable long id, @RequestBody GroceryInventoryItem item, Principal principal) {
        item.setGroceryItemId(id);
        User u = userService.findUserById(((UserAuthToken) principal).getAuthenticatedUserId());
        item.setUser(u);
        return new ResponseEntity<>(groService.saveItem(item), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<?> updateItem(@PathVariable long id, @RequestBody GroceryInventoryItem item, Principal principal){
        User u = userService.findUserById(((UserAuthToken) principal).getAuthenticatedUserId());
        item.setUser(u);
        return new ResponseEntity<>(groService.updateItem(item, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable long id) {
        groService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
