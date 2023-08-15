package com.parse.steam.services;

import com.parse.steam.dtos.ParseNamingDto;
import com.parse.steam.dtos.market.MarketElementDto;
import com.parse.steam.dtos.market.UpperMarketDto;
import com.parse.steam.dtos.parsed.ConditionDto;
import com.parse.steam.dtos.parsed.ItemDto;
import com.parse.steam.dtos.parsed.NamingDto;
import com.parse.steam.dtos.parsed.WeaponTypeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UpdateDBService {
    @Value("${steam.all-cs-item-url}")
    private String url;
    private final String pattern = "^(StatTrak™)?\\s?(Souvenir)?\\s?(.+?)\\s\\|\\s(.+?)\\s\\((.+?)\\)$";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<ItemDto> parseAllItems() {
        ResponseEntity<UpperMarketDto> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<MarketElementDto> items = response.getBody().getResults();
        if (items.size() != 0) {
            return items.stream().map(this::parseName).collect(Collectors.toList());
        }
        return null;
    }

    //TODO
    public boolean insert() {
        return false;
    }

    private ItemDto parseName(MarketElementDto dto) {
        ParseNamingDto parseNamingDto = stringToDto(dto.getName());
        ItemDto item = new ItemDto();
        item.setConditionDto(new ConditionDto(null, parseNamingDto.getCondition()));
        item.setWeaponTypeDto(new WeaponTypeDto(null, parseNamingDto.getWeapon()));
        item.setNamingDto(new NamingDto(null, null, parseNamingDto.getName()));
        item.setPhoto(dto.getAsset_description().getIcon_url());
        item.setSt(parseNamingDto.getSt());
        item.setSouvenir(parseNamingDto.getSouvenir());
        item.setActive(true);

        return item;
    }

    private ParseNamingDto stringToDto(String value) {
        ParseNamingDto dto = null;
        Matcher matcher = Pattern.compile(pattern).matcher(value);
        if (matcher.find()) {
            dto = new ParseNamingDto();
            dto.setSt(matcher.group(1) != null);
            dto.setSouvenir(matcher.group(2) != null);
            dto.setWeapon(matcher.group(3));
            dto.setName(matcher.group(4));
            dto.setCondition(matcher.group(5));
        }
        return dto;
    }

}
