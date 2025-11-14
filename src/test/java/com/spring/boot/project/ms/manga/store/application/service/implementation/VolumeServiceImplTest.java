package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.request.VolumeRequestDto;
import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.input.VolumePortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.model.Volume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VolumeServiceImplTest {

    private VolumePortIn volumePortIn;
    private MangaPortIn mangaPortIn;
    private VolumeServiceImpl volumeService;

    @BeforeEach
    void setUp() {
        volumePortIn = mock(VolumePortIn.class);
        mangaPortIn = mock(MangaPortIn.class);
        volumeService = new VolumeServiceImpl(volumePortIn, mangaPortIn);
    }

    @Test
    void create_ShouldCallVolumePortInWithCorrectVolume() {
        // Given
        VolumeRequestDto dto = new VolumeRequestDto();
        dto.setMangaId(1L);
        dto.setIsbn("978-1234567890");
        dto.setVolumeNumber(5);
        dto.setTitle("Attack on Titan Vol. 5");
        dto.setDescription("Eren faces new challenges...");
        dto.setPrice(BigDecimal.valueOf(49.99));
        dto.setStock(10);
        dto.setPublicationDate(LocalDate.of(2022, 5, 20));
        dto.setPages(200);
        dto.setLanguage("EN");

        Manga manga = new Manga(
                1L,
                "Attack on Titan",
                "Hajime Isayama",
                "Humanity vs Titans",
                34,
                LocalDate.of(2009, 9, 9)
        );

        when(mangaPortIn.getById(1L)).thenReturn(manga);

        // When
        volumeService.create(dto);

        // Then
        ArgumentCaptor<Volume> captor = ArgumentCaptor.forClass(Volume.class);
        verify(volumePortIn, times(1)).create(captor.capture());
        verify(mangaPortIn, times(1)).getById(1L);

        Volume captured = captor.getValue();

        // Assertions
        assertNotNull(captured);
        assertEquals("978-1234567890", captured.getIsbn());
        assertEquals(5, captured.getVolumeNumber());
        assertEquals("Attack on Titan Vol. 5", captured.getTitle());
        assertEquals("Eren faces new challenges...", captured.getDescription());
        assertEquals(BigDecimal.valueOf(49.99), captured.getPrice());
        assertEquals(10, captured.getStock());
        assertEquals(LocalDate.of(2022, 5, 20), captured.getPublicationDate());
        assertEquals(200, captured.getPages());
        assertEquals("EN", captured.getLanguage());
        assertTrue(captured.getAvailable());
        assertEquals(manga, captured.getManga());
    }
}
