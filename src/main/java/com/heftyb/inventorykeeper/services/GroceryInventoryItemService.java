package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.models.GroceryInventoryItem;

import java.util.List;

public interface GroceryInventoryItemService {
    List<GroceryInventoryItem> findAllItems();
    GroceryInventoryItem findItemById(long id);
    List<GroceryInventoryItem>findItemByName(String name);
    GroceryInventoryItem saveItem(GroceryInventoryItem item);
    GroceryInventoryItem updateItem(GroceryInventoryItem item, long id);
    void deleteItem(long id);
}
