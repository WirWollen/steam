package com.parse.steam.converters.parsed;

import com.parse.steam.dtos.parsed.ConditionDto;
import com.parse.steam.entities.parsed.ConditionEntity;
import org.springframework.beans.BeanUtils;

public class ConditionConverter {
    public static ConditionDto toDto(ConditionEntity entity) {
        ConditionDto dto = new ConditionDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static ConditionEntity toEntity(ConditionDto dto) {
        ConditionEntity entity = new ConditionEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
