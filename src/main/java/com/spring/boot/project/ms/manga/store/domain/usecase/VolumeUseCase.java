package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.input.VolumePortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Volume;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import com.spring.boot.project.ms.manga.store.domain.output.VolumePortOut;

import java.util.Optional;

public class VolumeUseCase implements VolumePortIn {
    private final VolumePortOut volumePortOut;
    private final MangaPortOut mangaPortOut;

    public VolumeUseCase(VolumePortOut volumePortOut, MangaPortOut mangaPortOut) {
        this.volumePortOut = volumePortOut;
        this.mangaPortOut = mangaPortOut;
    }

    @Override
    public void create(Volume volume) {
        Optional.ofNullable(volume)
                .ifPresent(volumePortOut::create);
    }

    @Override
    public void update(Volume volume) {
        Optional.ofNullable(volume)
                .ifPresent(volumePortOut::update);
    }

    @Override
    public Volume getByIsbn(String isbn) {
        Volume oldVolume = volumePortOut.getByIsbn(isbn);
        return Volume.builder()
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
                .available(oldVolume.getAvailable())
                .manga(mangaPortOut.getById(oldVolume.getManga().getId()))
                .build();
    }

}
