package com.hunt.hunter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hunt.hunter.model.Hunter;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Hunter}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HunterDto(UUID id, String name, String surName, String passportNo) implements Serializable {
}