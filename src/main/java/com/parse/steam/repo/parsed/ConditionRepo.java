package com.parse.steam.repo.parsed;

import com.parse.steam.entities.parsed.ConditionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ConditionRepo extends CrudRepository<ConditionEntity, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM condition WHERE condition.condition = ?1)", nativeQuery = true)
    boolean existsByCondition(String condition);
}
