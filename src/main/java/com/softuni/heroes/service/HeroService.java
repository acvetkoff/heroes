package com.softuni.heroes.service;

import com.softuni.heroes.model.service.HeroServiceModel;
import com.softuni.heroes.model.view.HeroDetailsViewModel;
import com.softuni.heroes.model.view.HeroViewModel;

import java.util.List;

public interface HeroService {

    HeroServiceModel create(HeroServiceModel heroServiceModel);

    List<HeroViewModel> getAll();

    HeroDetailsViewModel findById(String id);

    void delete(String id);
}
