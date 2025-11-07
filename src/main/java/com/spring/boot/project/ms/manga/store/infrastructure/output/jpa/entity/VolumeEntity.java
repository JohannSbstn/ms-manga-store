package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "volumenes_manga")
public class VolumeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "numero_volumen")
    private Integer volumeNumber;

    @Column(name = "titulo")
    private String title;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "precio")
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "fecha_publicacion")
    private LocalDate publicationDate;

    @Column(name = "paginas")
    private Integer pages;

    @Column(name = "idioma")
    private String language;

    @Column(name = "disponible")
    private Boolean available;

    @Column(name = "serie_id")
    private Long mangaId;

//    // 🔗 Relación con Manga
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "manga_id", nullable = false)
//    private MangaEntity manga;

//    // 🔗 Relación con Editorial (opcional)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "editorial_id")
//    private EditorialEntity editorial;
}
