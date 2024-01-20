package com.hunt.worker.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link AbstractEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntityDto(UUID id) implements Serializable {
}