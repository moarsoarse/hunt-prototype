package com.hunt.hunter.service;

import com.hunt.hunter.dto.HunterMapper;
import com.hunt.hunter.model.Hunter;
import com.hunt.hunter.repository.HunterRepository;
import com.hunt.worker.model.EntityService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class HunterServiceImpl implements EntityService<Hunter> {

    @NonNull
    protected HunterRepository hunterRepository;
    @NonNull
    protected HunterMapper hunterMapper;

    @Autowired
    HunterServiceImpl(HunterRepository hunterRepository, HunterMapper hunterMapper) {
        this.hunterRepository = hunterRepository;
        this.hunterMapper = hunterMapper;
    }


    @Override
    public TreeMap<String, Object> create(TreeMap<String, Object> treeMap) {
        Hunter newHunter = hunterRepository.save(hunterMapper.toEntity(treeMap));
        return hunterMapper.toDto(newHunter);
    }

    @Override
    public List<TreeMap<String, Object>> search(String s) {
        List<Hunter> searchResults = hunterRepository.findByNameLikeIgnoreCaseOrSurNameLikeIgnoreCaseAllIgnoreCase(s,s) ;
        return searchResults.stream().map(x -> hunterMapper.toDto(x)).collect(Collectors.toList());

    }
}


