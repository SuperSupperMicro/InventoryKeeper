package com.heftyb.inventorykeeper.repositories;

import com.heftyb.inventorykeeper.models.InventoryItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryItemRepository extends CrudRepository<InventoryItem, Long> {
    List<InventoryItem> findByNameContainingIgnoreCase(String name);
}
