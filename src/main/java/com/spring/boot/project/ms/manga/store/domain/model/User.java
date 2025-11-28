package com.spring.boot.project.ms.manga.store.domain.model;

import java.util.EnumSet;

public record User(
        Long id,
        String identityDocument,
        String email,
        String password,
        String name,
        String lastname,
        String phone,
        String address,
        boolean isActive,
        EnumSet<Role> roles
) {


    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder toBuilder() {
        return new UserBuilder(this);
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

        public UserBuilder() {
        }

        public UserBuilder(User user) {
            this.id = user.id;
            this.identityDocument = user.identityDocument;
            this.email = user.email;
            this.password = user.password;
            this.name = user.name;
            this.lastname = user.lastname;
            this.phone = user.phone;
            this.address = user.address;
            this.isActive = user.isActive;
            this.roles = user.roles;
        }

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
            return new User(this.id,
                    this.identityDocument,
                    this.email, this.password,
                    this.name,
                    this.lastname,
                    this.phone,
                    this.address,
                    this.isActive,
                    this.roles
            );
        }
    }
}
