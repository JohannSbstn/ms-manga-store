package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.exception.MangaNotExistException;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.MangaEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.MangaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MangaJpaAdapter implements MangaPortOut {
    private final MangaRepository mangaRepository;
    private final MangaEntityMapper mangaEntityMapper;

    @Override
    public Manga getById(Long mangaId) {
        return mangaRepository.findById(mangaId)
                .map(mangaEntityMapper::toModel)
                .orElseThrow(() -> new MangaNotExistException(mangaId));
    }
}
