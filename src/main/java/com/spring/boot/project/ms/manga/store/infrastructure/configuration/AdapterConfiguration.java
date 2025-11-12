package com.spring.boot.project.ms.manga.store.infrastructure.configuration;


import com.spring.boot.project.ms.manga.store.domain.output.UserPortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.UserRepository;
import com.spring.boot.project.ms.manga.store.domain.output.MangaPortOut;
import com.spring.boot.project.ms.manga.store.domain.output.VolumePortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter.MangaJpaAdapter;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter.VolumeJpaAdapter;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.MangaEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.VolumeEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.MangaRepository;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.VolumeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration {

    @Bean
    public UserPortOut userPortOut(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    public VolumePortOut volumePortOut(VolumeRepository volumeRepository, VolumeEntityMapper volumeEntityMapper) {
        return new VolumeJpaAdapter(volumeRepository, volumeEntityMapper);
    }

    @Bean
    public MangaPortOut mangaPortOut(MangaRepository mangaRepository, MangaEntityMapper mangaEntityMapper) {
        return new MangaJpaAdapter(mangaRepository, mangaEntityMapper);
    }

}
