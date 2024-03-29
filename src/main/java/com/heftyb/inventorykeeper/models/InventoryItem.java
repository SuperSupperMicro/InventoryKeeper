package com.heftyb.inventorykeeper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "InventoryItems")
public class InventoryItem extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryItemId;

    private String name;

    private String description;

    private String fdcId;

    @OneToMany(mappedBy = "inventoryItem", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "item")
    private Set<GroceryInventoryItem> groceryInventoryItems = new HashSet<>();

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
