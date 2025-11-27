package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.exception.MangaNotExistException;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MangaUseCaseTest {

    @Mock
    private MangaPortOut mangaPortOut;

    @InjectMocks
    private MangaUseCase mangaUseCase;

    @Test
    void getById_ShouldReturnManga_WhenExists() {
        // Arrange
        Manga manga = Manga.builder()
                .id(10L)
                .title("Naruto")
                .author("Masashi Kishimoto")
                .description("Ninja story")
                .totalVolumes(72)
                .build();

        when(mangaPortOut.getById(10L)).thenReturn(manga);

        // Act
        Manga result = mangaUseCase.getById(10L);

        // Assert
        assertNotNull(result);
        assertEquals("Naruto", result.title());
        verify(mangaPortOut, times(1)).getById(10L);
    }

    @Test
    void getById_ShouldThrowException_WhenMangaDoesNotExist() {
        // Arrange
        when(mangaPortOut.getById(99L))
                .thenThrow(new MangaNotExistException(99L));

        // Act + Assert
        MangaNotExistException exception = assertThrows(
                MangaNotExistException.class,
                () -> mangaUseCase.getById(99L)
        );

        assertEquals("the manga with id: 99 is not registered", exception.getMessage());
        verify(mangaPortOut, times(1)).getById(99L);
    }
}
