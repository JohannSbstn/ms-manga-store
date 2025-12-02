package com.spring.boot.project.ms.manga.store.domain.input;

import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MangaPortIn {
    Manga getById(Long mangaId);

    Page<Manga> getAll(Pageable pageable);
}
