package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.input.VolumePortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Volume;
import com.spring.boot.project.ms.manga.store.domain.output.VolumePortOut;
import org.springframework.stereotype.Component;

@Component
public class VolumeUseCase implements VolumePortIn {
    private final VolumePortOut volumePortOut;

    public VolumeUseCase(VolumePortOut volumePortOut) {
        this.volumePortOut = volumePortOut;
    }

    @Override
    public void create(Volume volume) {
        if (volume == null) {
            return;
        }
        volumePortOut.create(volume);
    }
}
