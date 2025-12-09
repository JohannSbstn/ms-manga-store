package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;
import com.spring.boot.project.ms.manga.store.application.service.MangaService;
import com.spring.boot.project.ms.manga.store.infrastructure.configuration.security.TokenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = MangaController.class,
        excludeAutoConfiguration = {
                SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class
        }
)
class MangaControllerTest {

    @MockitoBean
    private TokenService tokenService;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MangaService mangaService;

    @Test
    void getMangaById_ShouldReturn200AndResponseBody() throws Exception {

        MangaResponseDto response = new MangaResponseDto(
                "Naruto",
                "Masashi Kishimoto",
                "A ninja who wants to become Hokage",
                72.0,
                LocalDate.now()
        );

        Mockito.when(mangaService.getById(anyLong())).thenReturn(response);

        mockMvc.perform(get("/manga/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Naruto"))
                .andExpect(jsonPath("$.author").value("Masashi Kishimoto"))
                .andExpect(jsonPath("$.description").value("A ninja who wants to become Hokage"))
                .andExpect(jsonPath("$.totalVolumes").value(72));
    }

    @Test
    void getAll_ShouldReturn200AndPageResponse() throws Exception {

        MangaResponseDto dto = new MangaResponseDto(
                "One Piece",
                "Eiichiro Oda",
                "Pirate adventures",
                100,
                LocalDate.now()
        );

        com.spring.boot.project.ms.manga.store.domain.common.Page<MangaResponseDto> page =
                new com.spring.boot.project.ms.manga.store.domain.common.Page<>(
                        List.of(dto),
                        0,
                        10,
                        1,
                        1
                );

        Mockito.when(mangaService.getAll(any())).thenReturn(page);

        mockMvc.perform(get("/manga")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("One Piece"))
                .andExpect(jsonPath("$.content[0].author").value("Eiichiro Oda"))
                .andExpect(jsonPath("$.content[0].totalVolumes").value(100))
                .andExpect(jsonPath("$.totalElements").value(1));
    }
}
