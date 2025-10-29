package com.spring.boot.project.ms.manga.store.domain.model;

public class Role {
    private Long id;
    private RoleType type;

    public Role() {
    }

    public Role(Long id, RoleType type) {
        this.id = id;
        this.type = type;
    }

    public Role (RoleType type) {
        this(null, type);
    }

    // Factory methods
    public static Role userRole() {
        return new Role(RoleType.USER);
    }

    public static Role adminRole() {
        return new Role(RoleType.ADMIN);
    }

    public Long getId() {
        return id;
    }

    public RoleType getType() {
        return type;
    }
}
