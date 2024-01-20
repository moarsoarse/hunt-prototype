package com.hunt.hunter.dto;

import com.hunt.hunter.model.Hunter;
import com.hunt.worker.common.model.EntityMapper;
import org.mapstruct.*;

import java.util.TreeMap;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HunterMapper extends EntityMapper<Hunter> {


    Hunter toEntity(TreeMap<String,Object> hunterDto);

    TreeMap<String,Object> toDto(Hunter hunter);



    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Hunter partialUpdate(TreeMap<String,Object> hunterDto, @MappingTarget Hunter hunter);
}