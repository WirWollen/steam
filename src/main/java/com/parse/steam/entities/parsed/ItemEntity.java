package com.parse.steam.entities.parsed;

import com.parse.steam.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "item")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private ConditionEntity conditionEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    private WeaponTypeEntity weaponTypeEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    private NamingEntity namingEntity;
    @Column(name = "photo")
    private String photo;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "st")
    private Boolean st;
    @Column(name = "souvenir")
    private Boolean souvenir;
}
