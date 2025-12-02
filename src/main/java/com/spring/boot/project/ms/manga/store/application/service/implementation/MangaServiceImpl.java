package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;
import com.spring.boot.project.ms.manga.store.application.mapper.MangaDtoMapper;
import com.spring.boot.project.ms.manga.store.application.service.MangaService;
import com.spring.boot.project.ms.manga.store.domain.common.Page;
import com.spring.boot.project.ms.manga.store.domain.common.RequestPage;
import com.spring.boot.project.ms.manga.store.domain.exception.MangaNotExistException;
import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MangaServiceImpl implements MangaService {
    private final MangaPortIn mangaPortIn;
    private final MangaDtoMapper mangaDtoMapper;

    @Override
    public MangaResponseDto get(Long mangaId) {
        Manga manga = mangaPortIn.getById(mangaId);
        if (manga == null) {
            throw new MangaNotExistException(mangaId);
        }
        return new MangaResponseDto(
                manga.title(),
                manga.author(),
                manga.description(),
                manga.totalVolumes(),
                LocalDate.now()
        );
    }

    @Override
    public Page<MangaResponseDto> getAll(RequestPage pageRequest) {
        return mangaPortIn.getAll(pageRequest)
                .map(mangaDtoMapper::toMangaResponseDtos);
    }
}
