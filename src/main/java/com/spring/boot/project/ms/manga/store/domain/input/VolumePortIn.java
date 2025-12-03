package com.spring.boot.project.ms.manga.store.domain.input;

import com.spring.boot.project.ms.manga.store.domain.model.Volume;

public interface VolumePortIn {

    void create(Volume volume);

    void update(Volume volume);

    Volume getByIsbn(String isbn);
}
