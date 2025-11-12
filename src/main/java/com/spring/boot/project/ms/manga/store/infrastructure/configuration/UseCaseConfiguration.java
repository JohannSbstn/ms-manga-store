package com.spring.boot.project.ms.manga.store.infrastructure.configuration;

import com.spring.boot.project.ms.manga.store.domain.input.UserPortIn;
import com.spring.boot.project.ms.manga.store.domain.output.UserPortOut;
import com.spring.boot.project.ms.manga.store.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public UserPortIn userPortIn(UserPortOut userPortOut) {
        return new UserUseCase(userPortOut);
    }
}
