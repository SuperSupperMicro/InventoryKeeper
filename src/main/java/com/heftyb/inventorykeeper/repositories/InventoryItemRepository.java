package com.heftyb.inventorykeeper.repositories;

import com.heftyb.inventorykeeper.models.InventoryItem;
import org.springframework.data.repository.CrudRepository;

public interface InventoryItemRepository extends CrudRepository<InventoryItem, Integer> {
}
