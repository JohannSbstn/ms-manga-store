package com.spring.boot.project.ms.manga.store.domain.input;

import com.spring.boot.project.ms.manga.store.domain.common.Page;
import com.spring.boot.project.ms.manga.store.domain.common.RequestPage;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;

public interface MangaPortIn {
    Manga getById(Long mangaId);

    Page<Manga> getAll(RequestPage pageRequest);
}
