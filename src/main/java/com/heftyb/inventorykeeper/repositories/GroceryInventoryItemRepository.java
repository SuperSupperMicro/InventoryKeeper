package com.heftyb.inventorykeeper.repositories;

import com.heftyb.inventorykeeper.models.GroceryInventoryItem;
import org.springframework.data.repository.CrudRepository;

public interface GroceryInventoryItemRepository extends CrudRepository<GroceryInventoryItem, Long> {
}
