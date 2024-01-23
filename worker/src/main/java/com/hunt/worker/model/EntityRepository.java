package com.hunt.worker.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface EntityRepository<E extends AbstractEntity> extends JpaRepository<E, UUID> {

}