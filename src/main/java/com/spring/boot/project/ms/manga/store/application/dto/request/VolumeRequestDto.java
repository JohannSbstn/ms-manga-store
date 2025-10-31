package com.spring.boot.project.ms.manga.store.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VolumeRequestDto {

    @NotBlank(message = "the isbn can't be empty")
    private String isbn;

    private Integer volumeNumber;

    private String title;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private LocalDate publicationDate;

    private Integer pages;

    private String language;

    private Long mangaId;

}
