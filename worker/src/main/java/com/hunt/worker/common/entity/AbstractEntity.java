package com.hunt.worker.common.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity extends AbstractAuditable<UUID, UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @CreatedBy
    private UUID createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private UUID lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @Override
    public Optional<UUID> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }
    @Override
    public Optional<LocalDateTime > getCreatedDate() {
        return Optional.ofNullable(createdDate);
    }
    @Override
    public Optional<UUID> getLastModifiedBy() {
        return Optional.ofNullable(lastModifiedBy);
    }
    @Override
    public Optional<LocalDateTime > getLastModifiedDate() {
        return Optional.ofNullable(lastModifiedDate);
    }
    public AbstractEntity(TreeMap<String, Object> properties) {
        // Set properties from the map
        if (properties.containsKey("id")) {
            this.id = (UUID) properties.get("id");
        }
        if (properties.containsKey("createdBy")) {
            this.createdBy = (UUID) properties.get("createdBy");
        }
        if (properties.containsKey("createdDate")) {
            this.createdDate = (LocalDateTime) properties.get("createdDate");
        }
        if (properties.containsKey("lastModifiedBy")) {
            this.lastModifiedBy = (UUID) properties.get("lastModifiedBy");
        }
        if (properties.containsKey("lastModifiedDate")) {
            this.lastModifiedDate = (LocalDateTime) properties.get("lastModifiedDate");
        }
    }
}
