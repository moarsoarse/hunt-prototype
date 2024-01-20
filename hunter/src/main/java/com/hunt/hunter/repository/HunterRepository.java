package com.hunt.hunter.repository;

import com.hunt.hunter.model.Hunter;
import com.hunt.worker.common.model.EntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HunterRepository extends EntityRepository<Hunter> {
    List<Hunter> findByNameLikeIgnoreCaseOrSurNameLikeIgnoreCaseAllIgnoreCase(String name, String surName);
}