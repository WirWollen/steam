package com.parse.steam.converters.parsed;

import com.parse.steam.dtos.parsed.WeaponTypeDto;
import com.parse.steam.entities.parsed.WeaponTypeEntity;
import org.springframework.beans.BeanUtils;

public class WeaponTypeConverter {
    public static WeaponTypeDto toDto(WeaponTypeEntity entity) {
        WeaponTypeDto dto = new WeaponTypeDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static WeaponTypeEntity toEntity(WeaponTypeDto dto) {
        WeaponTypeEntity entity = new WeaponTypeEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
