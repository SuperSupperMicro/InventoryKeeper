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
    private long roleId;

    private String role;

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "role",
    allowSetters = true)
    private Set<UserRole> users = new HashSet<>();

    public Role() {
    }

    public Role(long roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roldId) {
        this.roleId = roldId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<UserRole> getUsers() {
        return users;
    }

    public void setUsers(Set<UserRole> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roldId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
