package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;
import com.spring.boot.project.ms.manga.store.application.mapper.MangaDtoMapper;
import com.spring.boot.project.ms.manga.store.domain.exception.MangaNotExistException;
import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MangaServiceImplTest {

    private MangaPortIn mangaPortIn;
    private MangaDtoMapper mangaDtoMapper;
    private MangaServiceImpl mangaService;

    @BeforeEach
    void setUp() {
        mangaPortIn = mock(MangaPortIn.class);
        mangaDtoMapper = mock(MangaDtoMapper.class);

        mangaService = new MangaServiceImpl(mangaPortIn, mangaDtoMapper);
    }

    @Test
    void testGet_ReturnsMangaResponseDto_WhenMangaExists() {
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

        MangaResponseDto response = mangaService.getById(mangaId);

        assertNotNull(response);
        assertEquals("One Piece", response.title());
        assertEquals("Eiichiro Oda", response.author());
        assertEquals("Pirates adventure", response.description());
        assertEquals(100, response.totalVolumes());
        assertEquals(LocalDate.now(), response.startDate());
        verify(mangaPortIn, times(1)).getById(mangaId);
    }

    @Test
    void testGet_ThrowsException_WhenMangaDoesNotExist() {
        Long mangaId = 1L;
        when(mangaPortIn.getById(mangaId)).thenReturn(null);

        assertThrows(MangaNotExistException.class, () -> mangaService.getById(mangaId));

        verify(mangaPortIn, times(1)).getById(mangaId);
    }
}
