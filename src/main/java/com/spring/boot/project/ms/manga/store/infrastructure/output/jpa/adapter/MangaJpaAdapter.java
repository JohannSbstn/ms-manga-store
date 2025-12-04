package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.common.Page;
import com.spring.boot.project.ms.manga.store.domain.common.RequestPage;
import com.spring.boot.project.ms.manga.store.domain.exception.MangaNotExistException;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.MangaEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.MangaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

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

    @Override
    public Page<Manga> getAll(RequestPage request) {

        var pageable = PageRequest.of(request.page(), request.size());

        var page = mangaRepository.findAll(pageable);

        var content = page.getContent()
                .stream()
                .map(mangaEntityMapper::toModel)
                .toList();

        return new Page<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
