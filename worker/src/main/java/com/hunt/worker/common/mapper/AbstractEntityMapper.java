package com.hunt.worker.common.mapper;

import com.hunt.worker.common.dto.AbstractEntityDto;
import com.hunt.worker.common.entity.AbstractEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AbstractEntityMapper {
	AbstractEntity toEntity(AbstractEntityDto abstractEntityDto);
	
	AbstractEntityDto toDto(AbstractEntity abstractEntity);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	AbstractEntity partialUpdate(AbstractEntityDto abstractEntityDto, @MappingTarget AbstractEntity abstractEntity);
}