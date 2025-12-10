package com.spring.boot.project.ms.manga.store.domain.common;

import java.util.List;
import java.util.function.Function;

public record Page<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {

    public <R> Page<R> map(Function<T, R> mapper) {

        List<R> newContent = content.stream()
                .map(mapper)
                .toList();

        return new Page<>(
                newContent,
                pageNumber,
                pageSize,
                totalElements,
                totalPages
        );
    }
}
