package com.parse.steam.converters.parsed;

import com.parse.steam.dtos.parsed.WearDto;
import com.parse.steam.entities.parsed.WearEntity;
import org.springframework.beans.BeanUtils;

public class WearConverter {
    public static WearDto toDto(WearEntity entity) {
        WearDto dto = new WearDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static WearEntity toEntity(WearDto dto) {
        WearEntity entity = new WearEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
