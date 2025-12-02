package com.spring.boot.project.ms.manga.store.domain.output;

import com.spring.boot.project.ms.manga.store.domain.common.Page;
import com.spring.boot.project.ms.manga.store.domain.common.RequestPage;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;

public interface MangaPortOut {
    Manga getById(Long mangaId);

    Page<Manga> getAll(RequestPage pageRequest);

}
