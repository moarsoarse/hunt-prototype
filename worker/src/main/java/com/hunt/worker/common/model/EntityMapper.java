package com.hunt.worker.common.model;


import org.mapstruct.*;

import java.util.TreeMap;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntityMapper {


    TreeMap<String,Object> entityToDto(AbstractEntity entity);


    AbstractEntity dtoToEntity(TreeMap<String,Object> dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AbstractEntity partialUpdate(EntityDto entityDto, @MappingTarget AbstractEntity abstractEntity);

    default String toString(Object value){
        return value.toString();
    }
}