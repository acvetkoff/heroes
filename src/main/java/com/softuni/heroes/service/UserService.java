package com.softuni.heroes.service;

import com.softuni.heroes.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel getByName(String username);
}
