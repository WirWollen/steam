package com.parse.steam.services;

import com.parse.steam.external.externalDtos.Item;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Item getData() {
        String fooResourceUrl = "https://steamcommunity.com/market/priceoverview/?country=RU&currency=5&appid=730&market_hash_name=AK-47 | Redline (Field-Tested)";
        ResponseEntity<Item> response = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
}
