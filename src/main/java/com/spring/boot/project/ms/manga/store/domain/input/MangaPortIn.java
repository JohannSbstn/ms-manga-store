package com.spring.boot.project.ms.manga.store.domain.input;

import com.spring.boot.project.ms.manga.store.domain.model.Manga;

public interface MangaPortIn {
    Manga getById(Long mangaId);
}
