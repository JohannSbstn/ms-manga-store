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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        VolumeRequestDto dto = new VolumeRequestDto(
                "978-1234567890",
                5.0,
                "Attack on Titan Vol. 5",
                "Eren faces new challenges...",
                BigDecimal.valueOf(49.99),
                10,
                LocalDate.of(2022, 5, 20),
                200,
                "EN",
                1L
        );

        Manga manga = Manga.builder()
                .id(1L)
                .title("Attack on Titan")
                .author("Hajime Isayama")
                .description("Humanity vs Titans")
                .totalVolumes(34)
                .startDate(LocalDate.of(2009, 9, 9))
                .build();

        when(mangaPortIn.getById(1L)).thenReturn(manga);

        // When
        volumeService.create(dto);

        // Then
        ArgumentCaptor<Volume> captor = ArgumentCaptor.forClass(Volume.class);
        verify(volumePortIn, times(1)).create(captor.capture());
        verify(mangaPortIn, times(1)).getById(1L);

        Volume captured = captor.getValue();

        assertNotNull(captured);
        assertEquals("978-1234567890", captured.isbn());
        assertEquals(5.0, captured.volumeNumber());
        assertEquals("Attack on Titan Vol. 5", captured.title());
        assertEquals("Eren faces new challenges...", captured.description());
        assertEquals(BigDecimal.valueOf(49.99), captured.price());
        assertEquals(10, captured.stock());
        assertEquals(LocalDate.of(2022, 5, 20), captured.publicationDate());
        assertEquals(200, captured.pages());
        assertEquals("EN", captured.language());
        assertTrue(captured.available());
        assertEquals(manga, captured.manga());
    }

    @Test
    void switchVolumeStatus_ShouldToggleAvailability_AndUpdateVolume() {
        // Given
        Manga manga = Manga.builder()
                .id(1L)
                .title("One Piece")
                .author("Oda")
                .description("Pirates")
                .totalVolumes(100)
                .startDate(LocalDate.of(1997, 7, 22))
                .build();

        Volume existing = Volume.builder()
                .id(10L)
                .isbn("999-1112223334")
                .volumeNumber(20.0)
                .title("One Piece Vol. 20")
                .description("Arc")
                .price(BigDecimal.TEN)
                .stock(15)
                .publicationDate(LocalDate.of(2020, 3, 15))
                .pages(180)
                .language("EN")
                .available(true)
                .manga(manga)
                .build();

        when(volumePortIn.getByIsbn("999-1112223334")).thenReturn(existing);
        when(mangaPortIn.getById(1L)).thenReturn(manga);

        // When
        volumeService.switchVolumeStatus("999-1112223334");

        // Then
        ArgumentCaptor<Volume> captor = ArgumentCaptor.forClass(Volume.class);
        verify(volumePortIn, times(1)).update(captor.capture());

        Volume updated = captor.getValue();

        assertEquals(existing.id(), updated.id());
        assertEquals(existing.isbn(), updated.isbn());
        assertFalse(updated.available());
        assertEquals(manga, updated.manga());

        assertEquals(existing.volumeNumber(), updated.volumeNumber());
        assertEquals(existing.title(), updated.title());
        assertEquals(existing.description(), updated.description());
        assertEquals(existing.price(), updated.price());
        assertEquals(existing.stock(), updated.stock());
        assertEquals(existing.publicationDate(), updated.publicationDate());
        assertEquals(existing.pages(), updated.pages());
        assertEquals(existing.language(), updated.language());
    }
}
