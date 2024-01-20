package com.hunt.hunter.controller;

import com.hunt.hunter.dto.HunterMapper;
import com.hunt.hunter.model.Hunter;
import com.hunt.hunter.repository.HunterRepository;
import com.hunt.hunter.service.HunterServiceImpl;
import com.hunt.worker.common.functions.Create;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

@RestController
@RequestMapping("/hunter")
@RequiredArgsConstructor
public class HunterController implements Create<Hunter> {

    private  HunterServiceImpl hunterService;
    private HunterRepository hunterRepository;
    private  HunterMapper hunterMapper;


    @PostMapping
    @Override
    public Optional<UUID> apply(TreeMap<String, Object> hunterMap) {
        Hunter hunter = hunterMapper.dtoToEntity(hunterMap);
        UUID newHunterId = hunterRepository.save(hunter).getId();
        return Optional.of(newHunterId);
    }
}