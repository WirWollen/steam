package com.parse.steam.beans;

import com.parse.steam.converters.parsed.ItemConverter;
import com.parse.steam.dtos.parsed.ItemDto;
import com.parse.steam.repo.parsed.ItemRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

//@Component
@Service
@RequiredArgsConstructor
@Slf4j
public class ItemFetcher {
    private final ItemRepo itemRepo;
    private final Set<ItemDto> fetchedItems;
    @Value(value = "${data.page-size}")
    private int pageSize;

    public boolean fetchItemsToKafka() {
        int currPage = 0;

        while (getItemsByPage(currPage).size() != 0) {
            Set<ItemDto> items = getItemsByPage(currPage);
            for (ItemDto item : items) {
                if (!fetchedItems.contains(item)) {
                    fetchedItems.add(item);
//                System.out.println("Fetched item: " + item);
                }
            }
            currPage++;
            System.out.println(currPage);
        }
        return true;
    }

    private Set<ItemDto> getItemsByPage(int currPage) {
        return itemRepo.findAll(PageRequest.of(currPage * pageSize / pageSize, pageSize)).getContent().stream().map(entity -> ItemConverter.toDto(entity)).collect(Collectors.toSet());
    }
}
