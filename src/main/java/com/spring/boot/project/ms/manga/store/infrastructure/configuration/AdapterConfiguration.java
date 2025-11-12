package com.spring.boot.project.ms.manga.store.infrastructure.configuration;


import com.spring.boot.project.ms.manga.store.domain.output.UserPortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration {

    @Bean
    public UserPortOut userPortOut(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
}
