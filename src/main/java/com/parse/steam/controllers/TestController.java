package com.parse.steam.controllers;

import com.parse.steam.dtos.ItemDto;
import com.parse.steam.services.RestTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final RestTemplateService restTemplateService;

    @GetMapping("/test")
    public ItemDto user() {
        return restTemplateService.getData();
    }
    @GetMapping("/test2")
    public ItemDto test(@RequestParam String hashName, @RequestParam String name, @RequestParam String quality) {
        return restTemplateService.getParamsData(hashName, name, quality);
    }
}
