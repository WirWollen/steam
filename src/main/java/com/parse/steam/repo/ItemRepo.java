package com.parse.steam.repo;

import com.parse.steam.entities.parsed.ItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepo extends CrudRepository<ItemEntity, Long> {
    List<ItemEntity> findAll();
}
