package com.hunt.worker.hunter.entity;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HunterMapper {
	Hunter toEntity(HunterDto hunterDto);

	HunterDto toDto(Hunter hunter);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Hunter partialUpdate(HunterDto hunterDto, @MappingTarget Hunter hunter);
}