package com.spring.boot.project.ms.manga.store.infrastructure.configuration;

import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter.MangaJpaAdapter;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.MangaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration {
    @Bean
    public MangaPortOut mangaPortOut(MangaRepository mangaRepository){
        return new MangaJpaAdapter(mangaRepository);
    }
}
