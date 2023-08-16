package com.parse.steam.repo.parsed;

import com.parse.steam.entities.parsed.NamingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NamingRepo extends CrudRepository<NamingEntity, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM naming WHERE naming_en = ?1)", nativeQuery = true)
    boolean existsByNamingEn(String namingEn);
}
