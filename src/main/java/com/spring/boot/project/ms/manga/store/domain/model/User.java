package com.spring.boot.project.ms.manga.store.domain.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String dni;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private String address;
    private boolean isActive;
    private List<Role> roles;

    public User(Long id, String dni, String email, String password, String name, String lastname, String phone, String address, boolean isActive, List<Role> roles) {
        this.id = id;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
        this.roles = roles != null ? new ArrayList<>(roles) : new ArrayList<>();
    }

    // Constructor for new users
    public User(String dni, String email, String password, String name, String lastname, String phone, String address) {
        this(null, dni, email, password, name, lastname, phone, address, true, new ArrayList<>());
        this.roles.add(Role.userRole());
    }

    // Domain methods
    public void assignRole(RoleType roleType) {
        if (roleType == null) {
            throw new IllegalArgumentException("RoleType cannot be null");
        }

        Role role = new Role(roleType);
        if (!this.roles.contains(role)) {
            this.roles.add(role);
        }
    }

    public void removeRole(RoleType roleType) {
        this.roles.removeIf(role -> role.getType() == roleType);
    }

    public boolean hasRole(RoleType roleType) {
        return this.roles.stream()
                .anyMatch(role -> role.getType() == roleType);
    }

    public boolean hasAnyRole(RoleType... roleTypes) {
        for (RoleType roleType : roleTypes) {
            if (hasRole(roleType)) {
                return true;
            }
        }
        return false;
    }

    public void promoteToAdmin() {
        if (!this.hasRole(RoleType.ADMIN)) {
            this.assignRole(RoleType.ADMIN);
        }
    }

    public void demoteFromAdmin() {
        this.removeRole(RoleType.ADMIN);
    }

    public boolean isAdmin() {
        return this.hasRole(RoleType.ADMIN);
    }

    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
