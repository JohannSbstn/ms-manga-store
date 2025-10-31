package com.spring.boot.project.ms.manga.store.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public class Manga {
    private Long id;
    private String title;
    private String author;
    private String description;
    private Integer totalVolumes;
    private LocalDate startDate;

    // Constructor
    public Manga(Long id, String title, String author, String description,
                 Integer totalVolumes, LocalDate startDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.totalVolumes = totalVolumes;
        this.startDate = startDate;
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
}
