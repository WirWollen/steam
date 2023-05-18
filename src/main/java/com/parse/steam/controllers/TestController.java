package com.parse.steam.controllers;

import com.parse.steam.external.externalDtos.Item;
import com.parse.steam.services.RestTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TestController {

    private final RestTemplateService restTemplateService;

    @GetMapping("/test")
    public Item user() {
        return restTemplateService.getData();
    }
}
