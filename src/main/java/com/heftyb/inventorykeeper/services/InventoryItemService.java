package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.models.InventoryItem;

import java.util.List;

public interface InventoryItemService {
    List<InventoryItem> findAll();
    InventoryItem findInventoryItemById(long id);
    InventoryItem save(InventoryItem inventoryItem);
    void delete(long id);
    InventoryItem update(InventoryItem inventoryItem, long id);
}
