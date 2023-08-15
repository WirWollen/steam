package com.parse.steam.entities.parsed;

import com.parse.steam.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "item")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity extends BaseEntity {
    @OneToMany(fetch = FetchType.LAZY)
    private List<WearEntity> wearEntities;
    @OneToMany(fetch = FetchType.LAZY)
    private List<WeaponTypeEntity> weaponTypeEntities;
    @OneToMany(fetch = FetchType.LAZY)
    private List<NamingEntity> namingEntities;
    @Column(name = "photo")
    private String photo;
    @Column(name = "st")
    private Boolean st;
    @Column(name = "souvenir")
    private Boolean souvenir;
}
