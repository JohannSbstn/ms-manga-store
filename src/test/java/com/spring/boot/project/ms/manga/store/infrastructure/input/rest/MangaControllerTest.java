package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;
import com.spring.boot.project.ms.manga.store.application.service.MangaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MangaController.class)
class MangaControllerTest {

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
}
