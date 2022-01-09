package com.softuni.heroes.service.impl;

import com.softuni.heroes.model.entity.Hero;
import com.softuni.heroes.model.service.HeroServiceModel;
import com.softuni.heroes.model.view.HeroDetailsViewModel;
import com.softuni.heroes.model.view.HeroViewModel;
import com.softuni.heroes.repository.HeroRepository;
import com.softuni.heroes.service.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HeroServiceModel create(HeroServiceModel heroServiceModel) {
        Hero hero = this.modelMapper.map(heroServiceModel, Hero.class);

        return this.modelMapper.map(this.heroRepository.saveAndFlush(hero), HeroServiceModel.class);
    }

    @Override
    public List<HeroViewModel> getAll() {
        return this.heroRepository
                .findAll()
                .stream()
                .map(hero -> {
                    HeroViewModel heroViewModel = this.modelMapper.map(hero, HeroViewModel.class);
                    heroViewModel.setImageUrl(this.getImageUrl(hero.getHeroClass().name().toLowerCase()));

                    return heroViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public HeroDetailsViewModel findById(String id) {
        return this.heroRepository
                .findById(id)
                .map(hero -> this.modelMapper.map(hero, HeroDetailsViewModel.class))
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        this.heroRepository.deleteById(id);
    }

    private String getImageUrl(String image) {
        return String.format("/img/%s.jpg", image);
    }
}
