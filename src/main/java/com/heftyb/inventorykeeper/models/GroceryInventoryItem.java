package com.heftyb.inventorykeeper.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

import static jakarta.persistence.TemporalType.DATE;

@Entity
@Table(name = "groceryInventoryItems")
public class GroceryInventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groceryItemId;

    @Temporal(DATE)
    private Date inDate;

    @Temporal(DATE)
    private Date expDate;

    private String qty;

    private String availableQty;

    @ManyToOne
    @JoinColumn(name = "inventoryItemId")
    private InventoryItem inventoryItem;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public GroceryInventoryItem() {
    }

    public GroceryInventoryItem(long groceryItemId, Date inDate, Date expDate, String qty, String availableQty, InventoryItem inventoryItem, User user) {
        this.groceryItemId = groceryItemId;
        this.inDate = inDate;
        this.expDate = expDate;
        this.qty = qty;
        this.availableQty = availableQty;
        this.inventoryItem = inventoryItem;
        this.user = user;
    }

    public long getGroceryItemId() {
        return groceryItemId;
    }

    public void setGroceryItemId(long groceryItemId) {
        this.groceryItemId = groceryItemId;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(String availableQty) {
        this.availableQty = availableQty;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem item) {
        this.inventoryItem = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GroceryInventoryItem{" +
                "groceryItemId=" + groceryItemId +
                ", inDate=" + inDate +
                ", expDate=" + expDate +
                ", qty='" + qty + '\'' +
                ", availableQty='" + availableQty + '\'' +
                ", item=" + inventoryItem +
                ", user=" + user +
                '}';
    }
}
