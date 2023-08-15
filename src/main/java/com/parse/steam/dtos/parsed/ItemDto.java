package com.parse.steam.dtos.parsed;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDto {
    private Long id;
    private List<WearDto> wearDtos;
    private List<WeaponTypeDto> weaponTypeDtos;
    private List<NamingDto> namingDtos;
    private String photo;
    private Boolean st;
    private Boolean souvenir;
}
