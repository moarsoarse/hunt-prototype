package com.hunt.worker.hunter.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link Hunter}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HunterDto implements Serializable {
	private UUID id;
	private UUID createdBy;
	private LocalDateTime createdDate;
	private UUID lastModifiedBy;
	private LocalDateTime lastModifiedDate;
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String surName;
}