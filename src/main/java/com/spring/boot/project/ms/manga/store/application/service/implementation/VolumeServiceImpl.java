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
public class VolumeServiceImpl implements VolumeService {

    private final VolumePortIn volumePortIn;
    private final MangaPortIn mangaPortIn;

    @Override
    public void create(VolumeRequestDto volumeRequestDto) {
        Manga manga = mangaPortIn.getById(volumeRequestDto.mangaId());
        Volume volume = Volume.builder()
                .isbn(volumeRequestDto.isbn())
                .volumeNumber(volumeRequestDto.volumeNumber())
                .title(volumeRequestDto.title())
                .description(volumeRequestDto.description())
                .price(volumeRequestDto.price())
                .stock(volumeRequestDto.stock())
                .publicationDate(volumeRequestDto.publicationDate())
                .pages(volumeRequestDto.pages())
                .language(volumeRequestDto.language())
                .available(true)
                .manga(manga)
                .build();
        volumePortIn.create(volume);
    }

    @Override
    public void switchVolumeStatus(String isbn) {
        Volume oldVolume = volumePortIn.getByIsbn(isbn);
        Volume newVolume = Volume.builder()
                .id(oldVolume.getId())
                .isbn(oldVolume.getIsbn())
                .volumeNumber(oldVolume.getVolumeNumber())
                .title(oldVolume.getTitle())
                .description(oldVolume.getDescription())
                .price(oldVolume.getPrice())
                .stock(oldVolume.getStock())
                .publicationDate(oldVolume.getPublicationDate())
                .pages(oldVolume.getPages())
                .language(oldVolume.getLanguage())
                .available(!oldVolume.getAvailable())
                .manga(mangaPortIn.getById(oldVolume.getManga().getId()))
                .build();
        volumePortIn.update(newVolume);
    }
}