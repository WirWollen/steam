package com.parse.steam.services;

import com.parse.steam.config.CacheBean;
import com.parse.steam.dtos.ParseNamingDto;
import com.parse.steam.dtos.market.MarketElementDto;
import com.parse.steam.dtos.market.UpperMarketDto;
import com.parse.steam.dtos.parsed.ConditionDto;
import com.parse.steam.dtos.parsed.ItemDto;
import com.parse.steam.dtos.parsed.NamingDto;
import com.parse.steam.dtos.parsed.WeaponTypeDto;
import com.parse.steam.repo.parsed.ConditionRepo;
import com.parse.steam.repo.parsed.ItemRepo;
import com.parse.steam.repo.parsed.NamingRepo;
import com.parse.steam.repo.parsed.WeaponTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateDBService {
    @Value("${steam.all-cs-item-url}")
    private String url;
    @Autowired
    private CacheBean cacheBean;
    private final ItemRepo itemRepo;
    private final NamingRepo namingRepo;
    private final ConditionRepo conditionRepo;
    private final WeaponTypeRepo weaponTypeRepo;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<ItemDto> parseAllItemsVisual() {
        ResponseEntity<UpperMarketDto> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<MarketElementDto> items = Objects.requireNonNull(response.getBody()).getResults();
        if (items.size() != 0) {
            List<ItemDto> itemDtos = items.stream().map(this::parseName).collect(Collectors.toList());
            itemDtos.forEach(this::insert);
            return itemDtos;
        }
        return null;
    }

    public boolean parseAllItemsSneaky() {
        ResponseEntity<UpperMarketDto> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        int totalSize = response.getBody().getTotal_count();
        for (int i = 0; i < totalSize; i+= 100) {
            ResponseEntity<UpperMarketDto> response2 = restTemplate.exchange(url + "start=" + i, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });
            List<MarketElementDto> items = Objects.requireNonNull(response2.getBody()).getResults();
            if (items.size() != 0) {
                items.stream().map(this::parseName).forEach(this::insert);
            }
        }
        return true;
    }

    @Transactional
    public void insert(ItemDto dto) {
        if (!namingRepo.existsByNamingEn(dto.getNamingDto().getNaming_en())) {
            namingRepo.checkAndInsertNamingEn(dto.getNamingDto().getNaming_en());
        }
        if (!(cacheBean.hashCondition().contains(dto.getConditionDto().getCondition()) || conditionRepo.existsByCondition(dto.getConditionDto().getCondition()))) {
            conditionRepo.checkAndInsertCondition(dto.getConditionDto().getCondition());
        }
        if (!(cacheBean.hashWeaponType().contains(dto.getWeaponTypeDto().getType()) || weaponTypeRepo.existsByType(dto.getWeaponTypeDto().getType()))) {
            weaponTypeRepo.checkAndInsertWeaponType(dto.getWeaponTypeDto().getType());
        }
        if (!itemRepo.findByParams(dto.getConditionDto().getCondition(), dto.getWeaponTypeDto().getType(), dto.getNamingDto().getNaming_en())) {
            itemRepo.saveByParams(
                    conditionRepo.getIdOfCondition(dto.getConditionDto().getCondition()),
                    weaponTypeRepo.getIdOfWeaponType(dto.getWeaponTypeDto().getType()),
                    namingRepo.getIdOfNaming(dto.getNamingDto().getNaming_en()),
                    dto.getPhoto(),
                    dto.getActive(),
                    dto.getSt(),
                    dto.getSouvenir()
            );
        }
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
        final String pattern = "^(StatTrak™)?\\s?(Souvenir)?\\s?(.+?)\\s\\|\\s(.+?)\\s\\((.+?)\\)$";
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
