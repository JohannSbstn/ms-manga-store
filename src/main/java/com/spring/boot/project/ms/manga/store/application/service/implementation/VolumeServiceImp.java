package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.request.VolumeRequestDto;
import com.spring.boot.project.ms.manga.store.application.service.VolumeService;
import com.spring.boot.project.ms.manga.store.domain.input.VolumePortIn;
import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.model.Volume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VolumeServiceImp implements VolumeService {

    private final VolumePortIn volumePortIn;
    private final MangaPortIn mangaPortIn;

    @Override
    public void create(VolumeRequestDto volumeRequestDto) {
        Manga manga = mangaPortIn.getById(volumeRequestDto.getMangaId());
        Volume volume = Volume.builder()
                .isbn(volumeRequestDto.getIsbn())
                .volumeNumber(volumeRequestDto.getVolumeNumber())
                .title(volumeRequestDto.getTitle())
                .description(volumeRequestDto.getDescription())
                .price(volumeRequestDto.getPrice())
                .stock(volumeRequestDto.getStock())
                .publicationDate(volumeRequestDto.getPublicationDate())
                .pages(volumeRequestDto.getPages())
                .language(volumeRequestDto.getLanguage())
                .available(true)
                .manga(manga)
                .build();
        volumePortIn.create(volume);
    }
}