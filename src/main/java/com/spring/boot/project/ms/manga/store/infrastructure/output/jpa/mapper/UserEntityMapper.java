package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper;

import com.spring.boot.project.ms.manga.store.domain.model.User;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(User user);

    @Mapping(target = "roles", ignore = true)
    User toModel(UserEntity userEntity);
}
