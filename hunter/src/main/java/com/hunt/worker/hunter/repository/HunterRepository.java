package com.hunt.worker.hunter.repository;

import com.hunt.worker.hunter.entity.Hunter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HunterRepository extends CrudRepository<Hunter, UUID> {
}
