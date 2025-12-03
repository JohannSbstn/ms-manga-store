package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.project.ms.manga.store.application.dto.request.VolumeRequestDto;
import com.spring.boot.project.ms.manga.store.application.service.VolumeService;
import com.spring.boot.project.ms.manga.store.infrastructure.configuration.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = VolumeController.class,
        excludeAutoConfiguration = {
                SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class
        }
)
class VolumeControllerTest {

    @MockitoBean
    private TokenService tokenService;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VolumeService volumeService;

    @Autowired
    private ObjectMapper objectMapper;

    private VolumeRequestDto dto;

    @BeforeEach
    void setUp() {
        dto = new VolumeRequestDto("978-1234567890",
                1.0,
                "Attack on Titan Vol. 1",
                "First volume of AOT",
                BigDecimal.valueOf(49.99),
                10,
                LocalDate.of(2020, 1, 1),
                200,
                "EN",
                1L
        );
    }

    @Test
    void createVolume_ShouldReturn201Created() throws Exception {
        // Arrange
        Mockito.doNothing().when(volumeService).create(Mockito.any(VolumeRequestDto.class));

        // Act & Assert
        mockMvc.perform(post("/volume")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        Mockito.verify(volumeService, Mockito.times(1)).create(Mockito.any(VolumeRequestDto.class));
    }

    @Test
    void switchVolumeStatus_ShouldReturn204_NoContent() throws Exception {
        // Arrange
        Mockito.doNothing().when(volumeService).switchVolumeStatus("978-1234567890");

        // Act & Assert
        mockMvc.perform(patch("/volume/978-1234567890"))
                .andExpect(status().isNoContent());

        Mockito.verify(volumeService, Mockito.times(1))
                .switchVolumeStatus("978-1234567890");
    }
}
