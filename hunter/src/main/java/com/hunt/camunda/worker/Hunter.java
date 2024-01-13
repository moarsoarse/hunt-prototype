package com.hunt.camunda.worker;

import com.hunt.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.UUID;

/**
 *
 *
 *
 */
@Getter
@Setter
public class Hunter extends AbstractAuditable<UUID,UUID> {

    private String name;
    private String surName;

}
