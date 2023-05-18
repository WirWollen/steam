package com.parse.steam.external.externalDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    private String success;
    private String lowest_price;
    private String volume;
    private String median_price;
}
