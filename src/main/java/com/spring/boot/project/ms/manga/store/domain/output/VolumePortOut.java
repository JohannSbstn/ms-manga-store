package com.spring.boot.project.ms.manga.store.domain.output;

import com.spring.boot.project.ms.manga.store.domain.model.Volume;

public interface VolumePortOut {

    void create(Volume volume);

    void update(Volume volume);

    Volume getByIsbn(String isbn);

}
