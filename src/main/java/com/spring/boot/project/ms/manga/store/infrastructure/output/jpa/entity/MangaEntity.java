package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "volumenes_manga")
public class MangaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private Integer volumeNumber;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private LocalDate publicationDate;
    private Integer pages;
    private String language;
    private Boolean available;

//    @ManyToOne
//    @JoinColumn(name = "serie_id", nullable = false)
//    private MangaSeriesEntity series;
//
//    // ⚙️ Solo informativo (no pertenece al dominio)
//    @ManyToOne
//    @JoinColumn(name = "editorial_id")
//    private PublisherEntity publisher;
}
