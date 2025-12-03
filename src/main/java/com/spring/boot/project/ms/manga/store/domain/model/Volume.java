package com.spring.boot.project.ms.manga.store.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Volume {
    private Long id;
    private String isbn;
    private Double volumeNumber;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private LocalDate publicationDate;
    private Integer pages;
    private String language;
    private Boolean available;

    private Manga manga;

    private Volume(VolumeBuilder volumeBuilder) {
        this.id = volumeBuilder.id;
        this.isbn = volumeBuilder.isbn;
        this.volumeNumber = volumeBuilder.volumeNumber;
        this.title = volumeBuilder.title;
        this.description = volumeBuilder.description;
        this.price = volumeBuilder.price;
        this.stock = volumeBuilder.stock;
        this.publicationDate = volumeBuilder.publicationDate;
        this.pages = volumeBuilder.pages;
        this.language = volumeBuilder.language;
        this.available = volumeBuilder.available;
        this.manga = volumeBuilder.manga;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public Double getVolumeNumber() {
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

    public Manga getManga() {
        return manga;
    }

    public static VolumeBuilder builder() {
        return new VolumeBuilder();
    }

    public static class VolumeBuilder {
        private Long id;
        private String isbn;
        private Double volumeNumber;
        private String title;
        private String description;
        private BigDecimal price;
        private Integer stock;
        private LocalDate publicationDate;
        private Integer pages;
        private String language;
        private Boolean available;
        private Manga manga;

        public VolumeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public VolumeBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public VolumeBuilder volumeNumber(Double volumeNumber) {
            this.volumeNumber = volumeNumber;
            return this;
        }

        public VolumeBuilder title(String title) {
            this.title = title;
            return this;
        }

        public VolumeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public VolumeBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public VolumeBuilder stock(Integer stock) {
            this.stock = stock;
            return this;
        }

        public VolumeBuilder publicationDate(LocalDate publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public VolumeBuilder pages(Integer pages) {
            this.pages = pages;
            return this;
        }

        public VolumeBuilder language(String language) {
            this.language = language;
            return this;
        }

        public VolumeBuilder available(Boolean available) {
            this.available = available;
            return this;
        }

        public VolumeBuilder manga(Manga manga) {
            this.manga = manga;
            return this;
        }

        public Volume build() {
            return new Volume(this);
        }
    }
}
