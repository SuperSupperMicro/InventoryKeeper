package com.heftyb.inventorykeeper.repositories;

import com.heftyb.inventorykeeper.models.GroceryInventoryItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroceryInventoryItemRepository extends CrudRepository<GroceryInventoryItem, Long> {
    @Query(value = "SELECT * g.grocery_item_id, g.in_date, g.exp_date, g.qty, g.available_qty  FROM  dbo.grovery_inventory_items g LEFT JOIN dbo.inventory_items i ON g.inventory_item = i.inventory_item_id WHERE  i.name = :name",
    nativeQuery = true)
    List<GroceryInventoryItem> findByItem(String name);
}
