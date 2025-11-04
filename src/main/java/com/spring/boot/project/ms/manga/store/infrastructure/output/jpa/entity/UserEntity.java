package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;

    @Column(name = "correo")
    private String email;

    @Column(name = "contrasena")
    private String password;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastname;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "direccion")
    private String address;

    @Column(name = "activo")
    private boolean isActive;

    @Column(name = "creado_en")
    private LocalDateTime createdAt;

    @Column(name = "actualizado_en")
    private LocalDateTime updatedAt;
}
