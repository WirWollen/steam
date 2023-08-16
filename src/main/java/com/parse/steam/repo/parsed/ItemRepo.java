package com.parse.steam.repo.parsed;

import com.parse.steam.entities.parsed.ItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepo extends CrudRepository<ItemEntity, Long> {
    List<ItemEntity> findAll();

    @Query(value = "SELECT EXISTS (SELECT 1 " +
            "FROM item " +
            "JOIN condition ON item.condition_id = condition.id " +
            "JOIN weapon_type ON item.weapon_type_id = weapon_type.id " +
            "JOIN naming ON item.naming_id = naming.id " +
            "WHERE condition.condition = ?1 AND weapon_type.type = ?2 AND naming.naming_en = ?3 " +
            "LIMIT 1)", nativeQuery = true)
    Boolean findByParams(String condition, String weaponType, String namingEn);

}
