package com.parse.steam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "naming")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NamingEntity extends BaseEntity {
    @Column(name = "naming_rus")
    private String naming_rus;
    @Column(name = "naming_en")
    private String naming_en;
}