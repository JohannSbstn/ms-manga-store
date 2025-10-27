package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.MangaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MangaJpaAdapter implements MangaPortOut {
    private final MangaRepository mangaRepository;

    @Override
    public void create(Manga manga) {

    }
}
