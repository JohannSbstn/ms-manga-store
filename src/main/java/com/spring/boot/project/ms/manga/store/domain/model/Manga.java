package com.spring.boot.project.ms.manga.store.domain.model;

import java.time.LocalDate;

public class Manga {
    private Long id;
    private String title;
    private String author;
    private String description;
    private Integer totalVolumes;
    private LocalDate startDate;

    private Manga(MangaBuilder mangaBuilder) {
        this.id = mangaBuilder.id;
        this.title = mangaBuilder.title;
        this.author = mangaBuilder.author;
        this.description = mangaBuilder.description;
        this.totalVolumes = mangaBuilder.totalVolumes;
        this.startDate = mangaBuilder.startDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTotalVolumes() {
        return totalVolumes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public static MangaBuilder builder() {
        return new MangaBuilder();
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
            return new Manga(this);
        }
    }
}
