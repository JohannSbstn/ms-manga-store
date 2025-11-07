package com.spring.boot.project.ms.manga.store.infrastructure.configuration;

import com.spring.boot.project.ms.manga.store.domain.input.VolumePortIn;
import com.spring.boot.project.ms.manga.store.domain.output.VolumePortOut;
import com.spring.boot.project.ms.manga.store.domain.usecase.VolumeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    public VolumePortIn volumePortIn(VolumePortOut volumePortOut) {
        return new VolumeUseCase(volumePortOut);
    }
}
