package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.model.Volume;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VolumeUseCaseTest {

    @Mock
    private VolumePortOut volumePortOut;

    private VolumeUseCase volumeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        volumeUseCase = new VolumeUseCase(volumePortOut);
    }

    @Test
    @DisplayName("Debería delegar la creación del volumen al puerto de salida con los datos correctos")
    void create_ShouldCallVolumePortOutOnce_WithCorrectVolume() {
        // Arrange
        Manga manga = new Manga(
                1L,
                "Attack on Titan",
                "Hajime Isayama",
                "Un manga sobre titanes y supervivencia",
                34,
                LocalDate.of(2009, 9, 9)
        );

        Volume volume = Volume.builder()
                .id(1L)
                .isbn("978-1234567890")
                .volumeNumber(1)
                .title("Attack on Titan Vol. 1")
                .description("Primer volumen de AOT")
                .price(BigDecimal.valueOf(49.99))
                .stock(10)
                .publicationDate(LocalDate.of(2020, 1, 1))
                .pages(200)
                .language("EN")
                .available(true)
                .manga(manga)
                .build();

        ArgumentCaptor<Volume> volumeCaptor = ArgumentCaptor.forClass(Volume.class);

        // Act
        volumeUseCase.create(volume);

        // Assert
        verify(volumePortOut, times(1)).create(volumeCaptor.capture());
        Volume captured = volumeCaptor.getValue();

        assertEquals(volume.getIsbn(), captured.getIsbn());
        assertEquals(volume.getTitle(), captured.getTitle());
        assertEquals(volume.getPrice(), captured.getPrice());
        assertEquals(volume.getStock(), captured.getStock());
        assertEquals(volume.getLanguage(), captured.getLanguage());
        assertEquals(volume.getManga().getId(), captured.getManga().getId());
        assertEquals(volume.getManga().getTitle(), captured.getManga().getTitle());
    }

    @Test
    @DisplayName("No debería llamar al puerto si el volumen es null (seguridad defensiva)")
    void create_ShouldNotCallPortOut_WhenVolumeIsNull() {
        // Act
        volumeUseCase.create(null);

        // Assert
        verify(volumePortOut, never()).create(any());
    }
}
