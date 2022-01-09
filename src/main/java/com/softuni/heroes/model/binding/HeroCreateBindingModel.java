package com.softuni.heroes.model.binding;

import com.softuni.heroes.model.entity.enums.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroCreateBindingModel {
    private String name;
    private Class heroClass;
    private Integer level;
}
