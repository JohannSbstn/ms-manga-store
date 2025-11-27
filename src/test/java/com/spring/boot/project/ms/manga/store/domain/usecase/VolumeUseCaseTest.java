package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.model.Volume;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import com.spring.boot.project.ms.manga.store.domain.output.VolumePortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VolumeUseCaseTest {

    @Mock
    private VolumePortOut volumePortOut;

    @Mock
    private MangaPortOut mangaPortOut;

    private VolumeUseCase volumeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        volumeUseCase = new VolumeUseCase(volumePortOut, mangaPortOut);
    }

    // --------------------------------------------------------------------
    @Test
    @DisplayName("Should delegate creation to the output port with correct data")
    void create_ShouldCallVolumePortOutOnce_WithCorrectVolume() {
        // Arrange
        Manga manga = Manga.builder()
                .id(1L)
                .title("Attack on Titan")
                .author("Hajime Isayama")
                .description("A manga about titans")
                .totalVolumes(34)
                .startDate(LocalDate.of(2009, 9, 9))
                .build();

        Volume volume = Volume.builder()
                .id(1L)
                .isbn("978-1234567890")
                .volumeNumber(1)
                .title("Vol 1")
                .description("First volume")
                .price(BigDecimal.valueOf(49.99))
                .stock(10)
                .publicationDate(LocalDate.of(2020, 1, 1))
                .pages(200)
                .language("EN")
                .available(true)
                .manga(manga)
                .build();

        ArgumentCaptor<Volume> captor = ArgumentCaptor.forClass(Volume.class);

        // Act
        volumeUseCase.create(volume);

        // Assert
        verify(volumePortOut, times(1)).create(captor.capture());
        Volume captured = captor.getValue();

        assertEquals(volume.isbn(), captured.isbn());
        assertEquals(volume.title(), captured.title());
        assertEquals(volume.price(), captured.price());
        assertEquals(volume.manga().id(), captured.manga().id());
    }

    // --------------------------------------------------------------------
    @Test
    @DisplayName("Should not call output port when volume is null")
    void create_ShouldNotCallPortOut_WhenVolumeIsNull() {
        // Act
        volumeUseCase.create(null);

        // Assert
        verify(volumePortOut, never()).create(any());
    }

    // --------------------------------------------------------------------
    @Test
    @DisplayName("Should delegate update to the output port")
    void update_ShouldCallPortOutUpdate() {
        // Arrange
        Volume volume = Volume.builder()
                .id(10L)
                .isbn("111-2223334445")
                .volumeNumber(5)
                .title("Test Update")
                .description("Desc")
                .price(BigDecimal.TEN)
                .stock(20)
                .publicationDate(LocalDate.now())
                .pages(150)
                .language("EN")
                .available(true)
                .manga(Manga.builder().id(1L).title("Test").build())
                .build();

        // Act
        volumeUseCase.update(volume);

        // Assert
        verify(volumePortOut, times(1)).update(volume);
    }

    // --------------------------------------------------------------------
    @Test
    @DisplayName("Should rebuild and return the volume from getByIsbn")
    void getByIsbn_ShouldReturnRebuiltVolume() {
        // Arrange
        Manga manga = Manga.builder()
                .id(1L)
                .title("One Piece")
                .author("Oda")
                .description("Pirates")
                .totalVolumes(100)
                .startDate(LocalDate.of(1997, 7, 22))
                .build();

        Volume oldVolume = Volume.builder()
                .id(10L)
                .isbn("999-1112223334")
                .volumeNumber(20)
                .title("One Piece Vol 20")
                .description("Arc")
                .price(BigDecimal.TEN)
                .stock(15)
                .publicationDate(LocalDate.of(2020, 3, 15))
                .pages(180)
                .language("EN")
                .available(true)
                .manga(manga)
                .build();

        when(volumePortOut.getByIsbn("999-1112223334")).thenReturn(oldVolume);
        when(mangaPortOut.getById(1L)).thenReturn(manga);

        // Act
        Volume result = volumeUseCase.getByIsbn("999-1112223334");

        // Assert
        assertNotNull(result);
        assertEquals(oldVolume.id(), result.id());
        assertEquals(oldVolume.isbn(), result.isbn());
        assertEquals(oldVolume.available(), result.available());
        assertEquals(manga.id(), result.manga().id());

        // Verify mocks
        verify(volumePortOut, times(1)).getByIsbn("999-1112223334");
        verify(mangaPortOut, times(1)).getById(1L);
    }
}