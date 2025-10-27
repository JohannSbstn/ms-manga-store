package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;

public class MangaUseCase implements MangaPortIn {
    private final MangaPortOut mangaPortOut;

    public MangaUseCase(MangaPortOut mangaPortOut) {
        this.mangaPortOut = mangaPortOut;
    }

    @Override
    public void create(Manga manga) {
        mangaPortOut.create(manga);
    }
}
