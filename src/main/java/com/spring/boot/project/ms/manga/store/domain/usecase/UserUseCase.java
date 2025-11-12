package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.input.UserPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.User;
import com.spring.boot.project.ms.manga.store.domain.output.UserPortOut;

public class UserUseCase implements UserPortIn {

    private final UserPortOut userPortOut;

    public UserUseCase(UserPortOut userPortOut) {
        this.userPortOut = userPortOut;
    }

    @Override
    public void create(User user) {
        userPortOut.create(user);
    }
}
