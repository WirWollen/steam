package com.parse.steam.converters.parsed;

import com.parse.steam.dtos.parsed.NamingDto;
import com.parse.steam.entities.parsed.NamingEntity;
import org.springframework.beans.BeanUtils;

public class NamingConverter {
    public static NamingDto toDto(NamingEntity entity) {
        NamingDto dto = new NamingDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static NamingEntity toEntity(NamingDto dto) {
        NamingEntity entity = new NamingEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
