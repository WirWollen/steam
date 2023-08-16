package com.parse.steam.repo.parsed;

import com.parse.steam.entities.parsed.WeaponTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WeaponTypeRepo extends CrudRepository<WeaponTypeEntity, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM weapon_type WHERE type = ?1)", nativeQuery = true)
    boolean existsByType(String type);
}
