package com.parse.steam.controllers;

import com.parse.steam.dtos.parsed.ItemDto;
import com.parse.steam.services.UpdateDBService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/update-db")
public class UpdateDBController {
    private final UpdateDBService updateDBService;

    @GetMapping("/start")
    public boolean startParse(int page) {
        return updateDBService.parseAllItemsSneaky(page);
    }

    @GetMapping("/visual")
    public List<ItemDto> visual() {
        return updateDBService.parseAllItemsVisual();
    }
}
