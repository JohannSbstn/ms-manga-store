package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.common.Page;
import com.spring.boot.project.ms.manga.store.domain.common.RequestPage;
import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;

public class MangaUseCase implements MangaPortIn {
    private final MangaPortOut mangaPortOut;

    public MangaUseCase(MangaPortOut mangaPortOut) {
        this.mangaPortOut = mangaPortOut;
    }

    @Override
    public Manga getById(Long mangaId) {
        return mangaPortOut.getById(mangaId);
    }

    @Override
    public Page<Manga> getAll(RequestPage pageRequest) {
        return mangaPortOut.getAll(pageRequest);
    }
}
