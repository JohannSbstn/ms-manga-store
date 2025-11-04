package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {

    @EmbeddedId
    private UserRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "usuario_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "rol_id")
    private RoleEntity role;

    @CreationTimestamp
    @Column(name = "creado_en", updatable = false)
    private LocalDateTime createdAt;

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserRoleId implements java.io.Serializable {

        private Long userId;
        private Long roleId;
    }
}
