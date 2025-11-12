package com.spring.boot.project.ms.manga.store;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void mainRuns() {
        Application.main(new String[]{"--spring.profiles.active=test"});
    }
}