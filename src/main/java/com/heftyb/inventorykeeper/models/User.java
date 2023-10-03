package com.heftyb.inventorykeeper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String username;
    private String email;

    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
        allowSetters = true)
    private Set<UserRole> roles = new HashSet<>();


    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "user")
    private Set<GroceryInventoryItem> groceryInventoryItems = new HashSet<>();

    public User() {
    }

    public User(long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<GroceryInventoryItem> getGroceryInventoryItems() {
        return groceryInventoryItems;
    }

    public void setGroceryInventoryItems(Set<GroceryInventoryItem> groceryInventoryItems) {
        this.groceryInventoryItems = groceryInventoryItems;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
