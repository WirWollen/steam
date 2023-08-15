package com.parse.steam.controllers;

import com.parse.steam.services.UpdateDBService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/update-db")
public class UpdateDBController {
    private final UpdateDBService updateDBService;

    @GetMapping("/start")
    public String startParse() {
        return updateDBService.parseAllItems();
    }
}
