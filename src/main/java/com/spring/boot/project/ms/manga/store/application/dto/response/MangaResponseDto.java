package com.spring.boot.project.ms.manga.store.application.dto.response;

import java.time.LocalDate;

public record MangaResponseDto(
        String title,

        String author,

        String description,

        Integer totalVolumes,

        LocalDate startDate
) {
}
