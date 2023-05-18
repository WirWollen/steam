package com.parse.steam.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {
    public ResponseEntity<String> getData() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://steamcommunity.com/market/priceoverview/?country=RU&currency=5&appid=730&market_hash_name=AK-47 | Redline (Field-Tested)";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response;
    }
}
