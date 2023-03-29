package com.heftyb.inventorykeeper.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    private long roldId;
    private String role;

    public Role() {
    }

    public Role(long roldId, String role) {
        this.roldId = roldId;
        this.role = role;
    }

    public long getRoldId() {
        return roldId;
    }

    public void setRoldId(long roldId) {
        this.roldId = roldId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roldId=" + roldId +
                ", role='" + role + '\'' +
                '}';
    }
}
