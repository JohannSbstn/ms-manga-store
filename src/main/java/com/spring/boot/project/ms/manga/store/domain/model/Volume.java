package com.spring.boot.project.ms.manga.store.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Volume(
        Long id,
        String isbn,
        Double volumeNumber,
        String title,
        String description,
        BigDecimal price,
        Integer stock,
        LocalDate publicationDate,
        Integer pages,
        String language,
        Boolean available,
        Manga manga
) {

    public static VolumeBuilder builder() {
        return new VolumeBuilder();
    }

    public VolumeBuilder toBuilder() {
        return new VolumeBuilder(this);
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
            return new Volume(this.id,
                    this.isbn,
                    this.volumeNumber,
                    this.title,
                    this.description,
                    this.price,
                    this.stock,
                    this.publicationDate,
                    this.pages,
                    this.language,
                    this.available,
                    this.manga);
        }

        public VolumeBuilder() {
        }

        public VolumeBuilder(Volume volume) {
            this.id = volume.id;
            this.isbn = volume.isbn;
            this.volumeNumber = volume.volumeNumber;
            this.title = volume.title;
            this.description = volume.description;
            this.price = volume.price;
            this.stock = volume.stock;
            this.publicationDate = volume.publicationDate;
            this.pages = volume.pages;
            this.language = volume.language;
            this.available = volume.available;
            this.manga = volume.manga;
        }
    }
}