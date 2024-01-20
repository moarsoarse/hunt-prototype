package com.hunt.worker.common.model;


import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;


@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<UUID> {

}
