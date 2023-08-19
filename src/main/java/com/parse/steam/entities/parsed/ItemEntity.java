package com.parse.steam.entities.parsed;

import com.parse.steam.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "item")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private ConditionEntity condition;
    @ManyToOne(fetch = FetchType.LAZY)
    private WeaponTypeEntity weaponType;
    @ManyToOne(fetch = FetchType.LAZY)
    private NamingEntity naming;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "st")
    private Boolean st;
    @Column(name = "souvenir")
    private Boolean souvenir;
}
