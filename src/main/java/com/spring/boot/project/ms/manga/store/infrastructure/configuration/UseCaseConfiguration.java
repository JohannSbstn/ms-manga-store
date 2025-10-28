package com.spring.boot.project.ms.manga.store.infrastructure.configuration;

import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import com.spring.boot.project.ms.manga.store.domain.usecase.MangaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    public MangaPortIn mangaPortIn(MangaPortOut mangaPortOut){
       return new MangaUseCase(mangaPortOut);
    }
}
