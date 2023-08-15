package com.parse.steam.converters.parsed;

import com.parse.steam.dtos.parsed.ItemDto;
import com.parse.steam.entities.parsed.ItemEntity;
import org.springframework.beans.BeanUtils;

public class ItemConverter {
    public static ItemDto toDto(ItemEntity entity) {
        ItemDto dto = new ItemDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static ItemEntity toEntity(ItemDto dto) {
        ItemEntity entity = new ItemEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
