package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.exception.VolumeAlreadyRegisteredException;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.model.Volume;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.VolumeEntity;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.VolumeEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.VolumeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VolumeJpaAdapterTest {

    private VolumeRepository volumeRepository;
    private VolumeEntityMapper volumeEntityMapper;
    private VolumeJpaAdapter volumeJpaAdapter;

    @BeforeEach
    void setUp() {
        volumeRepository = mock(VolumeRepository.class);
        volumeEntityMapper = mock(VolumeEntityMapper.class);
        volumeJpaAdapter = new VolumeJpaAdapter(volumeRepository, volumeEntityMapper);
    }

    @Test
    void create_ShouldSaveVolumeEntity_WhenIsbnDoesNotExist() {
        // Given
        Manga manga = Manga.builder()
                .id(1L)
                .title("Attack on Titan")
                .author("Hajime Isayama")
                .description("Titans")
                .totalVolumes(34)
                .startDate(LocalDate.of(2009, 9, 9))
                .build();
        Volume volume = Volume.builder()
                .isbn("978-1234567890")
                .title("Attack on Titan Vol. 5")
                .volumeNumber(5.0)
                .price(BigDecimal.valueOf(49.99))
                .manga(manga)
                .build();

        VolumeEntity entity = new VolumeEntity();
        entity.setIsbn("978-1234567890");

        when(volumeRepository.existsByIsbn("978-1234567890")).thenReturn(false);
        when(volumeEntityMapper.toEntity(volume)).thenReturn(entity);

        // When
        volumeJpaAdapter.create(volume);

        // Then
        verify(volumeRepository, times(1)).save(entity);
        assertEquals(1L, entity.getMangaId());
    }

    @Test
    void create_ShouldThrowException_WhenIsbnAlreadyExists() {
        // Given
        Volume volume = Volume.builder()
                .isbn("111-2223334445")
                .build();

        when(volumeRepository.existsByIsbn("111-2223334445")).thenReturn(true);

        // When & Then
        VolumeAlreadyRegisteredException exception = assertThrows(
                VolumeAlreadyRegisteredException.class,
                () -> volumeJpaAdapter.create(volume)
        );

        assertEquals(
                "the manga with isbn: 111-2223334445 is already registered",
                exception.getMessage()
        );
    }
}
