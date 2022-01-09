package com.softuni.heroes.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroViewModel {
    private String id;
    private String name;
    private String imageUrl;
}
