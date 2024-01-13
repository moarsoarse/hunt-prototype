package com.hunt.common.repository;

import com.hunt.common.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.UUID;

/**
 * Базовый репозиторий.
 *
 * @param <E> тип сущности
 * @author idurdyev
 */
@NoRepositoryBean
@Repos
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, UUID> {

    /**
     * Удаляет сущности с заданными ID и все зависящие от них сущности
     *
     * @param ids коллекция ID сущности
     */
    void deleteByIdIn(Collection<UUID> ids);
}
