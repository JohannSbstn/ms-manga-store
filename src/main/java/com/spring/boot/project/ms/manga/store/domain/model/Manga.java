package com.spring.boot.project.ms.manga.store.domain.model;

import java.time.LocalDate;

public record Manga(
        Long id,
        String title,
        String author,
        String description,
        Double totalVolumes,
        LocalDate startDate
) {

    public static MangaBuilder builder() {
        return new MangaBuilder();
    }

    public MangaBuilder toBuilder() {
        return new MangaBuilder(this);
    }

    public static class MangaBuilder {
        private Long id;
        private String title;
        private String author;
        private String description;
        private Integer totalVolumes;
        private LocalDate startDate;

        public MangaBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MangaBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MangaBuilder author(String author) {
            this.author = author;
            return this;
        }

        public MangaBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MangaBuilder totalVolumes(Integer totalVolumes) {
            this.totalVolumes = totalVolumes;
            return this;
        }

        public MangaBuilder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Manga build() {
            return new Manga(this.id,
                    this.title, this.author,
                    this.description,
                    this.totalVolumes,
                    this.startDate);
        }

        public MangaBuilder() {
        }

        public MangaBuilder(Manga manga) {
            this.id = manga.id;
            this.title = manga.title;
            this.author = manga.author;
            this.description = manga.description;
            this.totalVolumes = manga.totalVolumes;
            this.startDate = manga.startDate;
        }
    }
}
