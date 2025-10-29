package com.spring.boot.project.ms.manga.store.domain.input;

import com.spring.boot.project.ms.manga.store.domain.model.User;

public interface UserPortIn {
    void create(User user);
}
