package com.hunt.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * Базовый класс для Entity.
 * <p>У каждой сущности в БД должны быть эти поля.</p>
 *
 * @author idurdyev
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    /**
     * ID записи
     */

    private UUID id;

    /**
     * Момент создания записи в БД
     */

    private Instant created;

    /**
     * Момент последнего изменения записи в БД
     */
    private Instant updated;

    /**
     * ID пользователя, создавшего запись
     */
    private UUID createdBy;

    /**
     * ID последнего пользователя, изменившего запись
     */
    private UUID updatedBy;

    public String toString() {
        return "BaseEntity(id=" + this.getId() + ", created=" + this.getCreated() + ", updated=" + this.getUpdated() + ", createdBy=" + this.getCreatedBy() + ", updatedBy=" + this.getUpdatedBy() + ")";
    }
}
