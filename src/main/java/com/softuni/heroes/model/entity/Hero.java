package com.softuni.heroes.model.entity;

import com.softuni.heroes.model.entity.enums.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "heroes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero extends BaseEntity {
    private String name;

    @Enumerated(EnumType.STRING)
    private Class heroClass;

    private Integer level;
}
