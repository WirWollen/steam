package com.parse.steam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "wear")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WearEntity extends BaseEntity {
    @Column(name = "wear")
    private String wear;
}
