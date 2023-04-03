package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.exceptions.ResourceNotFoundException;
import com.heftyb.inventorykeeper.models.GroceryInventoryItem;
import com.heftyb.inventorykeeper.repositories.GroceryInventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "groceryInventoryItemService")
public class GroceryInventoryItemServiceImp implements GroceryInventoryItemService{
    @Autowired
    private GroceryInventoryItemRepository groRepo;

    @Override
    public List<GroceryInventoryItem> findAllItems() {
        List<GroceryInventoryItem> items = new ArrayList<>();
        groRepo.findAll()
                .iterator()
                .forEachRemaining(items::add);
        return items;
    }

    @Override
    public GroceryInventoryItem findItemById(long id) {
        return groRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grocery Item id " + id + " not found!"));
    }

    @Override
    public List<GroceryInventoryItem> findItemByName(String name) {
        List<GroceryInventoryItem> list = new ArrayList<>();
        groRepo.findByItem(name)
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public GroceryInventoryItem saveItem(GroceryInventoryItem item) {
        GroceryInventoryItem newItem = new GroceryInventoryItem();
        long id = item.getGroceryItemId();
        if (id != 0) {
            groRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Grocery Item id " + id + " not found!"));
            newItem.setGroceryItemId(id);
        }
        newItem.setInventoryItem(item.getInventoryItem());
        newItem.setInDate(item.getInDate());
        newItem.setExpDate(item.getExpDate());
        newItem.setQty(item.getQty());
        newItem.setAvailableQty(item.getAvailableQty());
        newItem.setUser(item.getUser());
        return groRepo.save(newItem);
    }

    @Transactional
    @Override
    public GroceryInventoryItem updateItem(GroceryInventoryItem item, long id) {
        GroceryInventoryItem gi = findItemById(id);

        if (item.getInDate() != null) {
            gi.setInDate(item.getInDate());
        }
        if (item.getExpDate() != null) {
            gi.setExpDate(item.getExpDate());
        }
        if (item.getQty() != null) {
            gi.setQty(item.getQty());
        }
        if (item.getAvailableQty() != null) {
            gi.setAvailableQty(item.getAvailableQty());
        }
        if (item.getInventoryItem() != null) {
            gi.setInventoryItem(item.getInventoryItem());
        }
        if (item.getUser() != null) {
            gi.setUser(item.getUser());
        }

        return groRepo.save(gi);
    }

    @Transactional
    @Override
    public void deleteItem(long id) {
        groRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grocery Item id " + id + " not found!"));
        groRepo.deleteById(id);
    }
}
