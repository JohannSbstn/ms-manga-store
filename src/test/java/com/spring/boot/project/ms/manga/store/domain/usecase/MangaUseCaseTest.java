package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
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
        Manga manga = Manga.builder()
                .id(10L)
                .title("Naruto")
                .author("Masashi Kishimoto")
                .description("Ninja story")
                .totalVolumes(72)
                .build();

        when(mangaPortOut.getById(10L)).thenReturn(manga);

        Manga result = mangaUseCase.getById(10L);

        assertNotNull(result);
        assertEquals("Naruto", result.title());
        verify(mangaPortOut, times(1)).getById(10L);
    }

    @Test
    void getById_ShouldPropagateException_WhenPortThrows() {
        RuntimeException ex = new RuntimeException("error test");
        when(mangaPortOut.getById(99L)).thenThrow(ex);

        RuntimeException thrown =
                assertThrows(RuntimeException.class, () -> mangaUseCase.getById(99L));

        assertEquals("error test", thrown.getMessage());
        verify(mangaPortOut, times(1)).getById(99L);
    }

    @Test
    void getAll_ShouldDelegateToPortOut() {
        Pageable pageable = mock(Pageable.class);
        Page<Manga> pageMock = mock(Page.class);

        when(mangaPortOut.getAll(pageable)).thenReturn(pageMock);

        Page<Manga> result = mangaUseCase.getAll(pageable);

        assertNotNull(result);
        assertEquals(pageMock, result);
        verify(mangaPortOut, times(1)).getAll(pageable);
    }
}
