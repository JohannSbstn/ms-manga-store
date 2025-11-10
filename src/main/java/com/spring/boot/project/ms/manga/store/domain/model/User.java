package com.spring.boot.project.ms.manga.store.domain.model;

import java.util.EnumSet;

public class User {

    private Long id;
    private String identityDocument;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private String address;
    private boolean isActive;
    private EnumSet<Role> roles;

    private User(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.identityDocument = userBuilder.identityDocument;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.name = userBuilder.name;
        this.lastname = userBuilder.lastname;
        this.phone = userBuilder.phone;
        this.address = userBuilder.address;
        this.isActive = userBuilder.isActive;
        this.roles = userBuilder.roles;
    }

    public Long getId() {
        return id;
    }

    public String getIdentityDocument() {
        return identityDocument;
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

    public EnumSet<Role> getRoles() {
        return roles != null ? EnumSet.copyOf(roles) : null;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String identityDocument;
        private String email;
        private String password;
        private String name;
        private String lastname;
        private String phone;
        private String address;
        private boolean isActive;
        private EnumSet<Role> roles;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder identityDocument(String identityDocument) {
            this.identityDocument = identityDocument;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder active(boolean active) {
            isActive = active;
            return this;
        }

        public UserBuilder roles(EnumSet<Role> roles) {
            this.roles = roles != null ? EnumSet.copyOf(roles) : null;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
