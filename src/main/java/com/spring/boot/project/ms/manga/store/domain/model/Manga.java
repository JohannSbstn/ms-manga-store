package com.spring.boot.project.ms.manga.store.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Manga {
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

    private MangaSeries series;

    public Manga(Long id, String isbn, Integer volumeNumber, String title, String description, BigDecimal price, Integer stock, LocalDate publicationDate, Integer pages, String language, Boolean available, MangaSeries series) {
        this.id = id;
        this.isbn = isbn;
        this.volumeNumber = volumeNumber;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.publicationDate = publicationDate;
        this.pages = pages;
        this.language = language;
        this.available = available;
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getVolumeNumber() {
        return volumeNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Integer getPages() {
        return pages;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getAvailable() {
        return available;
    }

    public MangaSeries getSeries() {
        return series;
    }
}
