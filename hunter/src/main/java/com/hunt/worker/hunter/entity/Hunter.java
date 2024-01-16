package com.hunt.worker.hunter.entity;

import com.hunt.worker.common.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.TreeMap;
import java.util.UUID;

/**
 *
 *
 *
 */
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "Hunter")
@Table(name = "HUNTER")
public class Hunter extends AbstractEntity {

    @ToString.Include
    private String name;
    @ToString.Include
    private String surName;

    public Hunter(TreeMap<String, Object> properties) {
        super(properties);
    }


    public Hunter() {
        this(new TreeMap<>() {
            {
                put("id", UUID.randomUUID());
            }
        });

	}
}
