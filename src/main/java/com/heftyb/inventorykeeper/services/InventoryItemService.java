package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.exceptions.ResourceNotFoundException;
import com.heftyb.inventorykeeper.models.InventoryItem;
import com.heftyb.inventorykeeper.repositories.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class InventoryItemService {

    @Autowired
    private InventoryItemRepository invRepo;

    public List<InventoryItem> findAll() {
        List<InventoryItem> inventoryItems = new ArrayList<>();

        invRepo.findAll()
                .iterator()
                .forEachRemaining(inventoryItems::add);

        return inventoryItems;
    }

    public InventoryItem findInventoryItemById(long id) throws ResourceNotFoundException {
        return invRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Inventory id " + id + " not found!")));
    }

    @Transactional
    public InventoryItem save(InventoryItem inventoryItem) {
        InventoryItem newInventoryItem = new InventoryItem();

        if (inventoryItem.getInventoryItemId() != 0) {
            invRepo.findById(inventoryItem.getInventoryItemId())
                    .orElseThrow(() -> new ResourceNotFoundException(("Inventory id " + inventoryItem.getInventoryItemId() + " not found!")));
            newInventoryItem.setInventoryItemId(inventoryItem.getInventoryItemId());
        }

        newInventoryItem.setName(inventoryItem.getName());
        newInventoryItem.setDescription(inventoryItem.getDescription());
        newInventoryItem.setFdcId(inventoryItem.getFdcId());

        return invRepo.save(newInventoryItem);
    }

    @Transactional
    public void delete(long id) {
        invRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory id " + id + " not found!"));
        invRepo.deleteById(id);
    }

    @Transactional
    public InventoryItem update(InventoryItem inventoryItem, long id) {

        InventoryItem savedItem = invRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory id " + id + " not found!"));

        savedItem.setName(inventoryItem.getName());
        savedItem.setDescription(inventoryItem.getDescription());
        savedItem.setFdcId(inventoryItem.getFdcId());

        return invRepo.save(savedItem);
    }
}
