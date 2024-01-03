package com.hunt.common.model;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Базовый класс для Entity.
 * <p>У каждой сущности в БД должны быть эти поля.</p>
 *
 *
 */
/*
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
*/

public class Entry implements Identifiable {

    /**
     * ID записи
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Момент создания записи в БД
     */
    @CreatedDate
    @Column(name = "created", updatable = false)
    private Instant created;

    /**
     * Момент последнего изменения записи в БД
     */
    @LastModifiedDate
    @Column(name = "updated")
    private Instant updated;

    /**
     * ID пользователя, создавшего запись
     */
    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private UUID createdBy;

    /**
     * ID последнего пользователя, изменившего запись
     */
    @Column(name = "updated_by")
    @LastModifiedBy
    private UUID updatedBy;

    public String toString() {
        return "BaseEntity(id=" + this.getId() + ", created=" + this.getCreated() + ", updated=" + this.getUpdated() + ", createdBy=" + this.getCreatedBy() + ", updatedBy=" + this.getUpdatedBy() + ")";
    }
}
