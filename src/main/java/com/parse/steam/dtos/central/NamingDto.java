package com.parse.steam.dtos.central;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NamingDto {
    private Long id;
    private String naming_rus;
    private String naming_en;
    private String photo;
}
