package com.hunt.hunter.service;

import com.hunt.hunter.model.Hunter;
import com.hunt.hunter.repository.HunterRepository;
import com.hunt.worker.common.model.EntityService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HunterServiceImpl implements EntityService<Hunter> {
    private final HunterRepository hunterRepository;
   // private final HunterMapper hunterMapper;



    @Override
    @Transactional
    @NonNull
    public Hunter create(Hunter hunter) {
        return hunterRepository.save(hunter);
    }
}


