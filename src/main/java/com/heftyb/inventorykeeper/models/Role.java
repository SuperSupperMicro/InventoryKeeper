package com.heftyb.inventorykeeper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roldId;

    private String role;

    @OneToMany(mappedBy = "role",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "role",
    allowSetters = true)
    private Set<UserRoles> users = new HashSet<>();

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

    public Set<UserRoles> getUsers() {
        return users;
    }

    public void setUsers(Set<UserRoles> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roldId=" + roldId +
                ", role='" + role + '\'' +
                '}';
    }
}
