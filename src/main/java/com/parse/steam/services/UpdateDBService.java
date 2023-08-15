package com.parse.steam.services;

import com.parse.steam.dtos.market.MarketElementDto;
import com.parse.steam.dtos.market.UpperMarketDto;
import com.parse.steam.dtos.parsed.ItemDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UpdateDBService {
    @Value("${steam.all-cs-item-url}")
    private String url;
    private final RestTemplate restTemplate = new RestTemplate();

    public String parseAllItems() {
        ResponseEntity<UpperMarketDto> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<MarketElementDto> items = response.getBody().getResults();
        if (items.size() != 0) {
            return items.get(0).getName();
        }
        return null;
    }

    private ItemDto parseName(MarketElementDto dto) {
        ItemDto item = new ItemDto();

//        item.setWearDtos();

        item.setPhoto(dto.getAsset_description().getIcon_url());
        //TODO
        item.setSt(false);
        //TODO
        item.setSouvenir(false);

        return item;
    }

}
