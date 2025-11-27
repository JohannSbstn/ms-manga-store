package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.request.VolumeRequestDto;
import com.spring.boot.project.ms.manga.store.application.service.VolumeService;
import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.input.VolumePortIn;
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
        Volume newVolume = oldVolume.toBuilder()
                .available(!oldVolume.available())
                .build();
        volumePortIn.update(newVolume);
    }
}