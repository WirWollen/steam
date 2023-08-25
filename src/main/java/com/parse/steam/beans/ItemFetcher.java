package com.parse.steam.beans;

import com.parse.steam.converters.parsed.ItemConverter;
import com.parse.steam.dtos.parsed.ItemDto;
import com.parse.steam.repo.parsed.ItemRepo;
import com.parse.steam.services.ParsedItemSenderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//@Component
@Service
@RequiredArgsConstructor
@Slf4j
public class ItemFetcher {
    private final ItemRepo itemRepo;
    @Value(value = "${data.page-size}")
    private int pageSize;
    private final ParsedItemSenderService sender;

    public boolean fetchItemsToKafka() {
        int currPage = 0;

        while (true) {
            Set<ItemDto> items = getItemsByPage(currPage);
            if (items.isEmpty()) {
                break;
            }
            items.stream().forEach(el -> sender.send(el));
            currPage++;
        }
        return true;
    }

    private Set<ItemDto> getItemsByPage(int currPage) {
        return itemRepo.findAll(PageRequest.of(currPage * pageSize / pageSize, pageSize)).getContent().stream().map(entity -> ItemConverter.toDto(entity)).collect(Collectors.toSet());
    }
}
