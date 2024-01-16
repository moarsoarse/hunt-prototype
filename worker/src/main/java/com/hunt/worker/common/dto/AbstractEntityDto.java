package com.hunt.worker.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * DTO for {@link com.hunt.worker.common.entity.AbstractEntity}
 */
public abstract class AbstractEntityDto implements Serializable {
	private UUID id;
	private UUID createdBy;
	private LocalDateTime createdDate;
	private UUID lastModifiedBy;
	private LocalDateTime lastModifiedDate;
	
	public AbstractEntityDto() {
	}
	
	public AbstractEntityDto(UUID id, UUID createdBy, LocalDateTime createdDate, UUID lastModifiedBy, LocalDateTime lastModifiedDate) {
		this.id = id;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public UUID getId() {
		return id;
	}
	
	public AbstractEntityDto setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public UUID getCreatedBy() {
		return createdBy;
	}
	
	public AbstractEntityDto setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
		return this;
	}
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	public AbstractEntityDto setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	
	public UUID getLastModifiedBy() {
		return lastModifiedBy;
	}
	
	public AbstractEntityDto setLastModifiedBy(UUID lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
		return this;
	}
	
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public AbstractEntityDto setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
		return this;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractEntityDto entity = (AbstractEntityDto) o;
		return Objects.equals(this.id, entity.id) &&
				Objects.equals(this.createdBy, entity.createdBy) &&
				Objects.equals(this.createdDate, entity.createdDate) &&
				Objects.equals(this.lastModifiedBy, entity.lastModifiedBy) &&
				Objects.equals(this.lastModifiedDate, entity.lastModifiedDate);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
				"id = " + id + ", " +
				"createdBy = " + createdBy + ", " +
				"createdDate = " + createdDate + ", " +
				"lastModifiedBy = " + lastModifiedBy + ", " +
				"lastModifiedDate = " + lastModifiedDate + ")";
	}
}