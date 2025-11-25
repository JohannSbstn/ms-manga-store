package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;
import com.spring.boot.project.ms.manga.store.domain.exception.MangaNotExistException;
import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MangaServiceImplTest {

    private MangaPortIn mangaPortIn;
    private MangaServiceImpl mangaService;

    @BeforeEach
    void setUp() {
        mangaPortIn = mock(MangaPortIn.class);
        mangaService = new MangaServiceImpl(mangaPortIn);
    }

    @Test
    void testGet_ReturnsMangaResponseDto_WhenMangaExists() {
        // Arrange
        Long mangaId = 1L;

        Manga manga = Manga.builder()
                .id(mangaId)
                .title("One Piece")
                .author("Eiichiro Oda")
                .description("Pirates adventure")
                .totalVolumes(100)
                .startDate(LocalDate.of(1997, 7, 22))
                .build();

        when(mangaPortIn.getById(mangaId)).thenReturn(manga);

        // Act
        MangaResponseDto response = mangaService.get(mangaId);

        // Assert
        assertNotNull(response);
        assertEquals("One Piece", response.title());
        assertEquals("Eiichiro Oda", response.author());
        assertEquals("Pirates adventure", response.description());
        assertEquals(100, response.totalVolumes());
        assertEquals(LocalDate.now(), response.startDate()); // the service sets NOW

        verify(mangaPortIn, times(1)).getById(mangaId);
    }

    @Test
    void testGet_ThrowsException_WhenMangaDoesNotExist() {
        // Arrange
        Long mangaId = 1L;
        when(mangaPortIn.getById(mangaId)).thenReturn(null);

        // Act & Assert
        assertThrows(MangaNotExistException.class, () -> mangaService.get(mangaId));

        verify(mangaPortIn, times(1)).getById(mangaId);
    }
}
