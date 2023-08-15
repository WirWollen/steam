package com.parse.steam.dtos.parsed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NamingDto {
    @JsonIgnore
    private Long id;
    private String naming_rus;
    private String naming_en;
}
