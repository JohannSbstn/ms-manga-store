package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.common.RequestPage;
import com.spring.boot.project.ms.manga.store.domain.exception.MangaNotExistException;
import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.MangaEntity;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.MangaEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.MangaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spring.boot.project.ms.manga.store.domain.common.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MangaJpaAdapterTest {

    @Mock
    private MangaRepository mangaRepository;

    @Mock
    private MangaEntityMapper mangaEntityMapper;

    @InjectMocks
    private MangaJpaAdapter mangaJpaAdapter;

    @Test
    void getById_ShouldReturnManga_WhenExists() {
        // Arrange
        MangaEntity entity = new MangaEntity();
        Manga model = Manga.builder()
                .id(1L)
                .title("One Piece")
                .author("Eichiro Oda")
                .description("A pirate adventure")
                .totalVolumes(100.0)
                .build();

        when(mangaRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(mangaEntityMapper.toModel(entity)).thenReturn(model);

        // Act
        Manga result = mangaJpaAdapter.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("One Piece", result.title());
        verify(mangaRepository).findById(1L);
        verify(mangaEntityMapper).toModel(entity);
    }

    @Test
    void getById_ShouldThrowException_WhenMangaDoesNotExist() {
        // Arrange
        when(mangaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act + Assert
        MangaNotExistException exception =
                assertThrows(MangaNotExistException.class, () -> mangaJpaAdapter.getById(99L));

        assertEquals("the manga with id: 99 is not registered", exception.getMessage());
        verify(mangaRepository).findById(99L);
        verifyNoInteractions(mangaEntityMapper);
    }

    @Test
    void getAll_ShouldReturnPageOfManga() {
        // Arrange
        RequestPage request = new RequestPage(0, 10);
        Pageable pageable = PageRequest.of(0, 10);

        MangaEntity entity = new MangaEntity();
        Manga model = Manga.builder()
                .id(1L)
                .title("Bleach")
                .author("Tite Kubo")
                .description("Soul reapers story")
                .totalVolumes(74)
                .build();

        org.springframework.data.domain.Page<MangaEntity> entityPage =
                new PageImpl<>(List.of(entity), pageable, 1);

        when(mangaRepository.findAll(pageable)).thenReturn(entityPage);
        when(mangaEntityMapper.toModel(entity)).thenReturn(model);

        // Act
        Page<Manga> result = mangaJpaAdapter.getAll(request);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.totalElements());
        assertEquals(1, result.totalPages());
        assertEquals(0, result.pageNumber());
        assertEquals(10, result.pageSize());
        assertEquals("Bleach", result.content().get(0).title());

        verify(mangaRepository).findAll(pageable);
        verify(mangaEntityMapper).toModel(entity);
    }
}
