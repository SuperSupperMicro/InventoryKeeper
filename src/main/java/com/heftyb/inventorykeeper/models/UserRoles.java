package com.heftyb.inventorykeeper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity

@Table(name = "userRoles")
public class UserRoles {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties(value = "roles")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "roleId")
    @JsonIgnoreProperties(value = "users")
    private Role role;

    public UserRoles() {
    }

    public UserRoles(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
