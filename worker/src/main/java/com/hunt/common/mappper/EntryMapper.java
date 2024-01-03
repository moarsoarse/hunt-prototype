package com.hunt.common.mappper;

import com.hunt.common.dto.EntryDTO;
import com.soarse.common.dto.BaseDTO;
import com.soarse.common.entity.BaseEntity;
import java.util.List;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Базовый маппер.
 *
 * @param <D> тип DTO
 * @param <E> тип сущности
 * @author idurdyev
 */
public interface EntryMapper<D extends EntryDTO, E extends Entry> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void update(D dto, @MappingTarget E entity);
}
