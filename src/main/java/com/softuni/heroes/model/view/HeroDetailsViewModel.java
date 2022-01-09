package com.softuni.heroes.model.view;

import com.softuni.heroes.model.entity.enums.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroDetailsViewModel {
    private String name;
    private Class heroClass;
    private Integer level;
}
