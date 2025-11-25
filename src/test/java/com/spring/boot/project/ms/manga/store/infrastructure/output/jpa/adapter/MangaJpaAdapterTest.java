package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

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
                .totalVolumes(100)
                .build();

        when(mangaRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(mangaEntityMapper.toModel(entity)).thenReturn(model);

        // Act
        Manga result = mangaJpaAdapter.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("One Piece", result.getTitle());
        verify(mangaRepository, times(1)).findById(1L);
        verify(mangaEntityMapper, times(1)).toModel(entity);
    }

    @Test
    void getById_ShouldThrowException_WhenMangaDoesNotExist() {
        // Arrange
        when(mangaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act + Assert
        MangaNotExistException exception = assertThrows(
                MangaNotExistException.class,
                () -> mangaJpaAdapter.getById(99L)
        );

        assertEquals("the manga with id: 99 is not registered", exception.getMessage());
        verify(mangaRepository, times(1)).findById(99L);
        verifyNoInteractions(mangaEntityMapper);
    }
}
