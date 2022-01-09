package com.softuni.heroes.model.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceModel {
    private String username;
    private String password;
    private String email;
    private String country;
}
