package com.spring.boot.project.ms.manga.store.domain.output;

import com.spring.boot.project.ms.manga.store.domain.model.Manga;

public interface MangaPortOut {
    void create(Manga manga);
}
