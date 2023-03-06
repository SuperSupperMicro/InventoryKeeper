package com.heftyb.inventorykeeper.models;

import jakarta.persistence.*;
import org.springframework.data.domain.Auditable;

@Entity
@Table(name = "InventoryItems")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryitemid;

    private String name;

    private String description;

    private String fdcId;


    public InventoryItem() {
    }

    public int getInventoryitemid() {
        return inventoryitemid;
    }

    public void setInventoryitemid(int inventoryitemid) {
        this.inventoryitemid = inventoryitemid;
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
                "inventoryitemid=" + inventoryitemid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fdcId='" + fdcId + '\'' +
                '}';
    }
}
