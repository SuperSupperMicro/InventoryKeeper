package com.heftyb.inventorykeeper.models;

import jakarta.persistence.*;

@Entity
@Table(name = "InventoryItems")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryItemId;

    private String name;

    private String description;

    private String fdcId;


    public InventoryItem() {
    }

    public InventoryItem(long inventoryItemId, String name, String description, String fdcId) {
        this.inventoryItemId = inventoryItemId;
        this.name = name;
        this.description = description;
        this.fdcId = fdcId;
    }

    public long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(long inventoryitemid) {
        this.inventoryItemId = inventoryitemid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFdcId() {
        return fdcId;
    }

    public void setFdcId(String fdcId) {
        this.fdcId = fdcId;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "inventoryitemid=" + inventoryItemId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fdcId='" + fdcId + '\'' +
                '}';
    }
}
