package com.spring.boot.project.ms.manga.store.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VolumeRequestDto {

    @NotBlank(message = "the isbn can't be empty")
    private String isbn;

    @NotNull(message = "the volume number is required")
    @Positive(message = "the volume number must be greater than 0")
    private Integer volumeNumber;

    @NotBlank(message = "the title can't be empty")
    private String title;

    @NotBlank(message = "the description can't be empty")
    private String description;

    @NotNull(message = "the price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "the price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "the stock is required")
    @Min(value = 0, message = "the stock can't be negative")
    private Integer stock;

    @PastOrPresent(message = "the publication date can't be in the future")
    private LocalDate publicationDate;

    @NotNull(message = "the number of pages is required")
    @Positive(message = "the number of pages must be greater than 0")
    private Integer pages;

    @NotBlank(message = "the language can't be empty")
    private String language;

    @NotNull(message = "the manga id is required")
    private Long mangaId;

}
